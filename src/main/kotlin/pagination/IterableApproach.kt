package pagination

fun fetchMergeRequestsIterable(gitLabService: GitLabService, lastProductionSha: String): List<MergeRequest> {
    val mrIterable = object : Iterable<List<MergeRequest>> {
        override fun iterator(): Iterator<List<MergeRequest>> {
            return object : Iterator<List<MergeRequest>> {
                var page = 1
                override fun hasNext() = true
                override fun next(): List<MergeRequest> = gitLabService.fetchMergeRequests(page++)
            }
        }
    }

    val pages = mrIterable
            .takeWhileInclusive { page -> page.none { it.commitSha == lastProductionSha } }
    return pages
            .flatten()
            .takeWhile { it.commitSha != lastProductionSha }
}

fun <T> Iterable<T>.takeWhileInclusive(pred: (T) -> Boolean): List<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = pred(it)
        result
    }
}

fun main(args: Array<String>) {
    //TODO not removing the last 3 commits

    val mrs = fetchMergeRequestsIterable(GitLabService(), "04d78f5c7cd51c52d0482d08224ff6a214da12c1")
    println(mrs)
    println(mrs.size)
}