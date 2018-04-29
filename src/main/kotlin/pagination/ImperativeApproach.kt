package pagination

fun fetchMergeRequests(gitLabService: GitLabService, lastProductionSha: String): List<MergeRequest> {
    var page = 1
    var mergeRequests = emptyList<MergeRequest>()

    // Fetch pages until we have the one that contains the commit we are looking for
    while (mergeRequests.none { it.commitSha == lastProductionSha }) {
        mergeRequests += gitLabService.fetchMergeRequests(page++)
    }

    // Trim the Merge Requests that are already in production
    val indexOfLastMergeRequestInProduction = mergeRequests.indexOfLast { it.commitSha == lastProductionSha }
    return mergeRequests.subList(0, indexOfLastMergeRequestInProduction)
}

fun main(args: Array<String>) {
    val mrs = fetchMergeRequests(GitLabService(), "04d78f5c7cd51c52d0482d08224ff6a214da12c1")
    println(mrs)
    println(mrs.size)
}