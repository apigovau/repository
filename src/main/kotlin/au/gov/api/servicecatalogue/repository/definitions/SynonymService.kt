package au.gov.api.servicecatalogue.repository.definitions

import au.gov.api.servicecatalogue.repository.RepositoryException
import com.beust.klaxon.JsonArray
import com.fasterxml.jackson.databind.ObjectMapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.json.JSONArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.sql.Array
import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource

data class SynonymExpansionResults(val expandedQuery: String, val usedSynonyms: Map<String, List<String>>)

@Component
class SynonymService {

    @Value("\${spring.datasource.url}")
    private var dbUrl: String? = null

    @Autowired
    private lateinit var dataSource: DataSource

    var originalSynonymWordWeighting = "^1.2"

    var origSynonyms = mutableListOf<List<String>>()

    private var synonyms : MutableMap<String, List<String>> = mutableMapOf()


    constructor(){}

    constructor(theDataSource: DataSource){
        dataSource = theDataSource
    }


    @EventListener(ApplicationReadyEvent::class)
    fun initialise() {

        if(synonyms.isEmpty()) {

            //@Suppress("UNCHECKED_CAST")
            for (synonymSet in getSynonyms()) {
                origSynonyms.add(synonymSet)

                for (synonymWord in synonymSet) {
                    if (synonymWord in synonyms) {
                        println("Duplicate synonym found: $synonymWord\n${synonyms[synonymWord]}\n$synonymSet")
                        System.exit(1)
                    }
                    synonyms[synonymWord] = synonymSet
                }
            }
        }
    }

    private fun getSynonyms() : List<List<String>> {
        var connection: Connection? = null
        try {
            connection = dataSource.connection

            val stmt = connection.createStatement()
            val rs = stmt.executeQuery("SELECT synonym FROM synonyms")
            val rv: MutableList<List<String>> = mutableListOf()
            while (rs.next()) {
                var value = rs.getString("synonym")
                rv.add(getSynonymList(value))
            }
            return rv.toList()

        } catch (e: Exception) {
            e.printStackTrace()
            throw RepositoryException()
        } finally {
            if (connection != null) connection.close()
        }
    }

    private fun getSynonymList(input:String):List<String>{
        var split = input.replace("\"","").replace("[","").replace("]","").split(',')
        var synonyms = mutableListOf<String>()
        for (word in split) {
            synonyms.add(word)
        }
        return synonyms
    }

    fun expand(input:String): SynonymExpansionResults {
        var output = ""

        val usedSynonyms = mutableMapOf<String, List<String>>()

        val tokens = getTokens(input)
        for(token in tokens){
            var workingToken = token
            val hadModifier = workingToken.startsWith("-") || workingToken.startsWith("+")
            var modifier = ""
            if(hadModifier){
                modifier = workingToken.substring(0,1)
                workingToken = workingToken.substring(1)
                //println("Had modifier of '${modifier}'. Now is : ${workingToken}")
            }
            val wasQuoted = workingToken.startsWith('"') && workingToken.endsWith('"')
            if(wasQuoted) workingToken = workingToken.removePrefix("\"").removeSuffix("\"")
            //println("workingToken: ${workingToken}")
            if(workingToken in synonyms){
                var expandedSynonyms = synonyms[workingToken]!!.map{ quoteIfNotMainSynonymAndHasSpaces(workingToken, it) }.joinToString(" ")

                val synonymAlternatives = synonyms[workingToken]!!.filter { it != workingToken }
                usedSynonyms[workingToken] = synonymAlternatives

                //println(expandedSynonyms)
                var origTokenWithWeight = token + originalSynonymWordWeighting
                if(wasQuoted) origTokenWithWeight = "\"$workingToken\"$originalSynonymWordWeighting"
                expandedSynonyms = expandedSynonyms.replace( workingToken , origTokenWithWeight)
                output = "$output$modifier($expandedSynonyms)"
            }
            else output +=  token
            output +=  " "
        }
        output = output.removeSuffix(" ")

        return SynonymExpansionResults(output, usedSynonyms.toMap())
    }

    private fun quoteIfNotMainSynonymAndHasSpaces(mainToken:String, token:String): String{
        if(token.contains(" ") && token!= mainToken) return "\"$token\""
        return token
    }

    private fun getTokens(input: String): List<String> {
        val theInput = quoteSynonyms(input)
        var workingInput = theInput
        val quoteMatcher = Regex("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
        for (match in quoteMatcher.findAll(theInput)) {
            val withoutSpaces = match.value.replace(" ", "~~")
            workingInput = workingInput.replace(match.value, withoutSpaces)
        }
        val workingTokens = workingInput.split(" ")
        return workingTokens.map {it.replace("~~"," ")}

    }

    private fun quoteSynonyms(input:String):String{
        var output = ""
        for(synonym in synonyms.keys.sortedByDescending { it.length }){
            val regex = Regex("\\b$synonym\\b")
            if(input.contains(regex) && synonym.contains(" ") && !input.contains("\"$synonym\"")) output += " \"$synonym\""
        }
        output = input + " " + output.replace("\"\"","\"").trim()
        return output.trim()
    }

    @Bean
    @Throws(SQLException::class)
    fun dataSource(): DataSource? {
        if (dbUrl?.isEmpty() ?: true) {
            return HikariDataSource()
        } else {
            val config = HikariConfig()
            config.jdbcUrl = dbUrl
            try {
                return HikariDataSource(config)
            } catch (e: Exception) {
                return null
            }
        }
    }
}