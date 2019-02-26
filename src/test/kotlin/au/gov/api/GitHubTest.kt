package au.gov.api

import org.junit.Assert
import org.junit.Test

import au.gov.api.servicecatalogue.repository.GitHub
import au.gov.api.servicecatalogue.repository.ServiceDescriptionRepositoryImpl
import au.gov.api.servicecatalogue.repository.WebRequestHandler
import org.springframework.beans.factory.annotation.Autowired

class GitHubTest{

    var repository = ServiceDescriptionRepositoryImpl(MockDataSource())
    var ghapi:GitHub = GitHub(WebRequestHandler(repository))


    @Test
    fun can_get_raw_github_uri_from_actual_uri(){

        val actualURI = "https://github.com/apigovau/api-gov-au-definitions/blob/master/api-documentation.md"
        val rawURI = "https://raw.githubusercontent.com/apigovau/api-gov-au-definitions/master/api-documentation.md"
        Assert.assertEquals(rawURI, GitHub.getRawURI(actualURI))
    }


    @Test
    fun can_get_text_of_github_file(){
        val uri="https://github.com/octocat/hello-worId/blob/master/README.md"
        val expectedContents = """hello-worId
===========

My first repository on GitHub.
"""

        Assert.assertEquals(expectedContents, GitHub.getTextOfFlie(uri))
    }

    @Test
    fun can_get_username_from_github_uri() {
        var username = GitHub.getUserGitHubUri("https://github.com/apigovau/service-catalogue-repository/blob/master/README.md")
        Assert.assertEquals(username,"apigovau")
    }


    @Test
    fun can_get_reponame_from_github_uri() {
        var reponame = GitHub.getRepoGitHubUri("https://github.com/apigovau/service-catalogue-repository/blob/master/README.md")
        Assert.assertEquals(reponame,"service-catalogue-repository")
    }

    fun iniTestDB()
    {
        var issues = WebRequestHandler.ResponseContentChacheEntry(request_uri = "https://api.github.com/apigovau/octocat/api-gov-au-definitions/issues",content = "[ { \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/7\", \"repository_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions\", \"labels_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/7/labels{/name}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/7/comments\", \"events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/7/events\", \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions/issues/7\", \"id\": 412166752, \"node_id\": \"MDU6SXNzdWU0MTIxNjY3NTI=\", \"number\": 7, \"title\": \"This is another dummy issue\", \"user\": { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false }, \"labels\": [ ], \"state\": \"open\", \"locked\": false, \"assignee\": { \"login\": \"Ben-MN\", \"id\": 13549736, \"node_id\": \"MDQ6VXNlcjEzNTQ5NzM2\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/13549736?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/Ben-MN\", \"html_url\": \"https://github.com/Ben-MN\", \"followers_url\": \"https://api.github.com/users/Ben-MN/followers\", \"following_url\": \"https://api.github.com/users/Ben-MN/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/Ben-MN/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/Ben-MN/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/Ben-MN/subscriptions\", \"organizations_url\": \"https://api.github.com/users/Ben-MN/orgs\", \"repos_url\": \"https://api.github.com/users/Ben-MN/repos\", \"events_url\": \"https://api.github.com/users/Ben-MN/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/Ben-MN/received_events\", \"type\": \"User\", \"site_admin\": false }, \"assignees\": [ { \"login\": \"Ben-MN\", \"id\": 13549736, \"node_id\": \"MDQ6VXNlcjEzNTQ5NzM2\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/13549736?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/Ben-MN\", \"html_url\": \"https://github.com/Ben-MN\", \"followers_url\": \"https://api.github.com/users/Ben-MN/followers\", \"following_url\": \"https://api.github.com/users/Ben-MN/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/Ben-MN/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/Ben-MN/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/Ben-MN/subscriptions\", \"organizations_url\": \"https://api.github.com/users/Ben-MN/orgs\", \"repos_url\": \"https://api.github.com/users/Ben-MN/repos\", \"events_url\": \"https://api.github.com/users/Ben-MN/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/Ben-MN/received_events\", \"type\": \"User\", \"site_admin\": false }, { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false } ], \"milestone\": null, \"comments\": 0, \"created_at\": \"2019-02-19T23:07:25Z\", \"updated_at\": \"2019-02-19T23:07:50Z\", \"closed_at\": null, \"author_association\": \"MEMBER\", \"body\": \"This is a dummy issue created for testing data ingestion from GitHub\\r\\n\\r\\nDid you know that issues can have text formatting like\\r\\n\\r\\n- Lists\\r\\n\\r\\n- Bold\\r\\n\\r\\n- \\r\\n\\r\\n1. An ordered list\\r\\n\\r\\nYou can also mention people @thezaza101 \" }, { \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/6\", \"repository_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions\", \"labels_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/6/labels{/name}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/6/comments\", \"events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/6/events\", \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions/issues/6\", \"id\": 412166234, \"node_id\": \"MDU6SXNzdWU0MTIxNjYyMzQ=\", \"number\": 6, \"title\": \"This issue can be closed immediately\", \"user\": { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false }, \"labels\": [ { \"id\": 1164808662, \"node_id\": \"MDU6TGFiZWwxMTY0ODA4NjYy\", \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/labels/help%20wanted\", \"name\": \"help wanted\", \"color\": \"008672\", \"default\": true } ], \"state\": \"open\", \"locked\": false, \"assignee\": { \"login\": \"colugo\", \"id\": 9936116, \"node_id\": \"MDQ6VXNlcjk5MzYxMTY=\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/9936116?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/colugo\", \"html_url\": \"https://github.com/colugo\", \"followers_url\": \"https://api.github.com/users/colugo/followers\", \"following_url\": \"https://api.github.com/users/colugo/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/colugo/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/colugo/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/colugo/subscriptions\", \"organizations_url\": \"https://api.github.com/users/colugo/orgs\", \"repos_url\": \"https://api.github.com/users/colugo/repos\", \"events_url\": \"https://api.github.com/users/colugo/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/colugo/received_events\", \"type\": \"User\", \"site_admin\": false }, \"assignees\": [ { \"login\": \"colugo\", \"id\": 9936116, \"node_id\": \"MDQ6VXNlcjk5MzYxMTY=\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/9936116?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/colugo\", \"html_url\": \"https://github.com/colugo\", \"followers_url\": \"https://api.github.com/users/colugo/followers\", \"following_url\": \"https://api.github.com/users/colugo/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/colugo/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/colugo/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/colugo/subscriptions\", \"organizations_url\": \"https://api.github.com/users/colugo/orgs\", \"repos_url\": \"https://api.github.com/users/colugo/repos\", \"events_url\": \"https://api.github.com/users/colugo/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/colugo/received_events\", \"type\": \"User\", \"site_admin\": false }, { \"login\": \"Ben-MN\", \"id\": 13549736, \"node_id\": \"MDQ6VXNlcjEzNTQ5NzM2\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/13549736?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/Ben-MN\", \"html_url\": \"https://github.com/Ben-MN\", \"followers_url\": \"https://api.github.com/users/Ben-MN/followers\", \"following_url\": \"https://api.github.com/users/Ben-MN/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/Ben-MN/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/Ben-MN/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/Ben-MN/subscriptions\", \"organizations_url\": \"https://api.github.com/users/Ben-MN/orgs\", \"repos_url\": \"https://api.github.com/users/Ben-MN/repos\", \"events_url\": \"https://api.github.com/users/Ben-MN/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/Ben-MN/received_events\", \"type\": \"User\", \"site_admin\": false } ], \"milestone\": null, \"comments\": 2, \"created_at\": \"2019-02-19T23:05:33Z\", \"updated_at\": \"2019-02-19T23:11:08Z\", \"closed_at\": null, \"author_association\": \"MEMBER\", \"body\": \"This is a dummy issue created for testing data ingestion from GitHub\" }, { \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/5\", \"repository_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions\", \"labels_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/5/labels{/name}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/5/comments\", \"events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/5/events\", \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions/issues/5\", \"id\": 412166019, \"node_id\": \"MDU6SXNzdWU0MTIxNjYwMTk=\", \"number\": 5, \"title\": \"There are no issues in this repository\", \"user\": { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false }, \"labels\": [ { \"id\": 1164808663, \"node_id\": \"MDU6TGFiZWwxMTY0ODA4NjYz\", \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/labels/good%20first%20issue\", \"name\": \"good first issue\", \"color\": \"7057ff\", \"default\": true } ], \"state\": \"open\", \"locked\": false, \"assignee\": { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false }, \"assignees\": [ { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false } ], \"milestone\": null, \"comments\": 1, \"created_at\": \"2019-02-19T23:04:50Z\", \"updated_at\": \"2019-02-19T23:08:38Z\", \"closed_at\": null, \"author_association\": \"MEMBER\", \"body\": \"This is a dummy issue created for testing data ingestion from GitHub\" } ]")
        var pullReqs = WebRequestHandler.ResponseContentChacheEntry(request_uri = "https://api.github.com/apigovau/octocat/api-gov-au-definitions/pulls", content = "[ { \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9\", \"id\": 254508763, \"node_id\": \"MDExOlB1bGxSZXF1ZXN0MjU0NTA4NzYz\", \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions/pull/9\", \"diff_url\": \"https://github.com/apigovau/api-gov-au-definitions/pull/9.diff\", \"patch_url\": \"https://github.com/apigovau/api-gov-au-definitions/pull/9.patch\", \"issue_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/9\", \"number\": 9, \"state\": \"open\", \"locked\": false, \"title\": \"Update api-documentation.md\", \"user\": { \"login\": \"thezaza101\", \"id\": 27200279, \"node_id\": \"MDQ6VXNlcjI3MjAwMjc5\", \"avatar_url\": \"https://avatars1.githubusercontent.com/u/27200279?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/thezaza101\", \"html_url\": \"https://github.com/thezaza101\", \"followers_url\": \"https://api.github.com/users/thezaza101/followers\", \"following_url\": \"https://api.github.com/users/thezaza101/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/thezaza101/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/thezaza101/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/thezaza101/subscriptions\", \"organizations_url\": \"https://api.github.com/users/thezaza101/orgs\", \"repos_url\": \"https://api.github.com/users/thezaza101/repos\", \"events_url\": \"https://api.github.com/users/thezaza101/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/thezaza101/received_events\", \"type\": \"User\", \"site_admin\": false }, \"body\": \"This is a dummy pull request created for testing data ingestion from GitHub\", \"created_at\": \"2019-02-20T05:51:47Z\", \"updated_at\": \"2019-02-20T05:51:47Z\", \"closed_at\": null, \"merged_at\": null, \"merge_commit_sha\": \"16fcb80a10bf81d0fa01d780040aea5fa4428ba9\", \"assignee\": null, \"assignees\": [ ], \"requested_reviewers\": [ ], \"requested_teams\": [ ], \"labels\": [ ], \"milestone\": null, \"commits_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9/commits\", \"review_comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9/comments\", \"review_comment_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/comments{/number}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/9/comments\", \"statuses_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/statuses/048e5ad478d84a5cd228108cc1f7a3b10ded79b3\", \"head\": { \"label\": \"apigovau:thezaza101-patch-1\", \"ref\": \"thezaza101-patch-1\", \"sha\": \"048e5ad478d84a5cd228108cc1f7a3b10ded79b3\", \"user\": { \"login\": \"apigovau\", \"id\": 34371447, \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjM0MzcxNDQ3\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/34371447?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/apigovau\", \"html_url\": \"https://github.com/apigovau\", \"followers_url\": \"https://api.github.com/users/apigovau/followers\", \"following_url\": \"https://api.github.com/users/apigovau/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/apigovau/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/apigovau/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/apigovau/subscriptions\", \"organizations_url\": \"https://api.github.com/users/apigovau/orgs\", \"repos_url\": \"https://api.github.com/users/apigovau/repos\", \"events_url\": \"https://api.github.com/users/apigovau/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/apigovau/received_events\", \"type\": \"Organization\", \"site_admin\": false }, \"repo\": { \"id\": 162195566, \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNjIxOTU1NjY=\", \"name\": \"api-gov-au-definitions\", \"full_name\": \"apigovau/api-gov-au-definitions\", \"private\": false, \"owner\": { \"login\": \"apigovau\", \"id\": 34371447, \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjM0MzcxNDQ3\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/34371447?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/apigovau\", \"html_url\": \"https://github.com/apigovau\", \"followers_url\": \"https://api.github.com/users/apigovau/followers\", \"following_url\": \"https://api.github.com/users/apigovau/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/apigovau/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/apigovau/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/apigovau/subscriptions\", \"organizations_url\": \"https://api.github.com/users/apigovau/orgs\", \"repos_url\": \"https://api.github.com/users/apigovau/repos\", \"events_url\": \"https://api.github.com/users/apigovau/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/apigovau/received_events\", \"type\": \"Organization\", \"site_admin\": false }, \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions\", \"description\": null, \"fork\": false, \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions\", \"forks_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/forks\", \"keys_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/keys{/key_id}\", \"collaborators_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/collaborators{/collaborator}\", \"teams_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/teams\", \"hooks_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/hooks\", \"issue_events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/events{/number}\", \"events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/events\", \"assignees_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/assignees{/user}\", \"branches_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/branches{/branch}\", \"tags_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/tags\", \"blobs_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/blobs{/sha}\", \"git_tags_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/tags{/sha}\", \"git_refs_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/refs{/sha}\", \"trees_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/trees{/sha}\", \"statuses_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/statuses/{sha}\", \"languages_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/languages\", \"stargazers_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/stargazers\", \"contributors_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/contributors\", \"subscribers_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/subscribers\", \"subscription_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/subscription\", \"commits_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/commits{/sha}\", \"git_commits_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/commits{/sha}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/comments{/number}\", \"issue_comment_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/comments{/number}\", \"contents_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/contents/{+path}\", \"compare_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/compare/{base}...{head}\", \"merges_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/merges\", \"archive_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/{archive_format}{/ref}\", \"downloads_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/downloads\", \"issues_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues{/number}\", \"pulls_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls{/number}\", \"milestones_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/milestones{/number}\", \"notifications_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/notifications{?since,all,participating}\", \"labels_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/labels{/name}\", \"releases_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/releases{/id}\", \"deployments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/deployments\", \"created_at\": \"2018-12-17T22:06:37Z\", \"updated_at\": \"2019-02-19T23:18:18Z\", \"pushed_at\": \"2019-02-20T05:51:48Z\", \"git_url\": \"git://github.com/apigovau/api-gov-au-definitions.git\", \"ssh_url\": \"git@github.com:apigovau/api-gov-au-definitions.git\", \"clone_url\": \"https://github.com/apigovau/api-gov-au-definitions.git\", \"svn_url\": \"https://github.com/apigovau/api-gov-au-definitions\", \"homepage\": null, \"size\": 1343, \"stargazers_count\": 0, \"watchers_count\": 0, \"language\": \"Kotlin\", \"has_issues\": true, \"has_projects\": true, \"has_downloads\": true, \"has_wiki\": true, \"has_pages\": false, \"forks_count\": 1, \"mirror_url\": null, \"archived\": false, \"open_issues_count\": 5, \"license\": { \"key\": \"mit\", \"name\": \"MIT License\", \"spdx_id\": \"MIT\", \"url\": \"https://api.github.com/licenses/mit\", \"node_id\": \"MDc6TGljZW5zZTEz\" }, \"forks\": 1, \"open_issues\": 5, \"watchers\": 0, \"default_branch\": \"master\" } }, \"base\": { \"label\": \"apigovau:master\", \"ref\": \"master\", \"sha\": \"be73da012216890b62876967930b95874ac77360\", \"user\": { \"login\": \"apigovau\", \"id\": 34371447, \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjM0MzcxNDQ3\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/34371447?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/apigovau\", \"html_url\": \"https://github.com/apigovau\", \"followers_url\": \"https://api.github.com/users/apigovau/followers\", \"following_url\": \"https://api.github.com/users/apigovau/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/apigovau/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/apigovau/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/apigovau/subscriptions\", \"organizations_url\": \"https://api.github.com/users/apigovau/orgs\", \"repos_url\": \"https://api.github.com/users/apigovau/repos\", \"events_url\": \"https://api.github.com/users/apigovau/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/apigovau/received_events\", \"type\": \"Organization\", \"site_admin\": false }, \"repo\": { \"id\": 162195566, \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNjIxOTU1NjY=\", \"name\": \"api-gov-au-definitions\", \"full_name\": \"apigovau/api-gov-au-definitions\", \"private\": false, \"owner\": { \"login\": \"apigovau\", \"id\": 34371447, \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjM0MzcxNDQ3\", \"avatar_url\": \"https://avatars3.githubusercontent.com/u/34371447?v=4\", \"gravatar_id\": \"\", \"url\": \"https://api.github.com/users/apigovau\", \"html_url\": \"https://github.com/apigovau\", \"followers_url\": \"https://api.github.com/users/apigovau/followers\", \"following_url\": \"https://api.github.com/users/apigovau/following{/other_user}\", \"gists_url\": \"https://api.github.com/users/apigovau/gists{/gist_id}\", \"starred_url\": \"https://api.github.com/users/apigovau/starred{/owner}{/repo}\", \"subscriptions_url\": \"https://api.github.com/users/apigovau/subscriptions\", \"organizations_url\": \"https://api.github.com/users/apigovau/orgs\", \"repos_url\": \"https://api.github.com/users/apigovau/repos\", \"events_url\": \"https://api.github.com/users/apigovau/events{/privacy}\", \"received_events_url\": \"https://api.github.com/users/apigovau/received_events\", \"type\": \"Organization\", \"site_admin\": false }, \"html_url\": \"https://github.com/apigovau/api-gov-au-definitions\", \"description\": null, \"fork\": false, \"url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions\", \"forks_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/forks\", \"keys_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/keys{/key_id}\", \"collaborators_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/collaborators{/collaborator}\", \"teams_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/teams\", \"hooks_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/hooks\", \"issue_events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/events{/number}\", \"events_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/events\", \"assignees_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/assignees{/user}\", \"branches_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/branches{/branch}\", \"tags_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/tags\", \"blobs_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/blobs{/sha}\", \"git_tags_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/tags{/sha}\", \"git_refs_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/refs{/sha}\", \"trees_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/trees{/sha}\", \"statuses_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/statuses/{sha}\", \"languages_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/languages\", \"stargazers_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/stargazers\", \"contributors_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/contributors\", \"subscribers_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/subscribers\", \"subscription_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/subscription\", \"commits_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/commits{/sha}\", \"git_commits_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/git/commits{/sha}\", \"comments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/comments{/number}\", \"issue_comment_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/comments{/number}\", \"contents_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/contents/{+path}\", \"compare_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/compare/{base}...{head}\", \"merges_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/merges\", \"archive_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/{archive_format}{/ref}\", \"downloads_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/downloads\", \"issues_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues{/number}\", \"pulls_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls{/number}\", \"milestones_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/milestones{/number}\", \"notifications_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/notifications{?since,all,participating}\", \"labels_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/labels{/name}\", \"releases_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/releases{/id}\", \"deployments_url\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/deployments\", \"created_at\": \"2018-12-17T22:06:37Z\", \"updated_at\": \"2019-02-19T23:18:18Z\", \"pushed_at\": \"2019-02-20T05:51:48Z\", \"git_url\": \"git://github.com/apigovau/api-gov-au-definitions.git\", \"ssh_url\": \"git@github.com:apigovau/api-gov-au-definitions.git\", \"clone_url\": \"https://github.com/apigovau/api-gov-au-definitions.git\", \"svn_url\": \"https://github.com/apigovau/api-gov-au-definitions\", \"homepage\": null, \"size\": 1343, \"stargazers_count\": 0, \"watchers_count\": 0, \"language\": \"Kotlin\", \"has_issues\": true, \"has_projects\": true, \"has_downloads\": true, \"has_wiki\": true, \"has_pages\": false, \"forks_count\": 1, \"mirror_url\": null, \"archived\": false, \"open_issues_count\": 5, \"license\": { \"key\": \"mit\", \"name\": \"MIT License\", \"spdx_id\": \"MIT\", \"url\": \"https://api.github.com/licenses/mit\", \"node_id\": \"MDc6TGljZW5zZTEz\" }, \"forks\": 1, \"open_issues\": 5, \"watchers\": 0, \"default_branch\": \"master\" } }, \"_links\": { \"self\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9\" }, \"html\": { \"href\": \"https://github.com/apigovau/api-gov-au-definitions/pull/9\" }, \"issue\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/9\" }, \"comments\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/issues/9/comments\" }, \"review_comments\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9/comments\" }, \"review_comment\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/comments{/number}\" }, \"commits\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/pulls/9/commits\" }, \"statuses\": { \"href\": \"https://api.github.com/repos/apigovau/api-gov-au-definitions/statuses/048e5ad478d84a5cd228108cc1f7a3b10ded79b3\" } }, \"author_association\": \"MEMBER\" } ]")
        repository.saveCache(issues)
        repository.saveCache(pullReqs)
    }

    @Test
    fun can_get_issues_from_github() {
        iniTestDB()
        val issues = ghapi.getIssues("apigovau","api-gov-au-definitions")
        Assert.assertEquals(4,issues.count())
    }

    @Test
    fun can_get_pulls_from_github() {
        iniTestDB()
        val issues = ghapi.getPullRequests("apigovau","api-gov-au-definitions")
        Assert.assertEquals(1,issues.count())
    }
    @Test
    fun can_get_convos_from_github() {
        iniTestDB()
        val convos = ghapi.getGitHubConvos("apigovau","api-gov-au-definitions")
        Assert.assertEquals(5,convos.count())
    }

    @Test
    fun can_limit_convos_from_github() {
        iniTestDB()
        val convos = ghapi.getGitHubConvos("apigovau","api-gov-au-definitions",limit = 3)
        Assert.assertEquals(3,convos.count())
    }

    @Test
    fun can_get_convo_on_github_uri() {
        iniTestDB()
        var uri = "https://github.com/apigovau/api-gov-au-definitions/blob/master/README.md"
        val convos = ghapi.getGitHubConvos(GitHub.getUserGitHubUri(uri),GitHub.getRepoGitHubUri(uri))
        Assert.assertEquals(5,convos.count())
    }






}
