package pagination

import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureTimeMillis

fun fetchMergeRequestsSequence(gitLabService: GitLabService, lastProductionSha: String): Sequence<MergeRequest> {
    val mrSequence = buildSequence {
        var page = 1
        while (true) yieldAll(gitLabService.fetchMergeRequests(page++))
    }

    return mrSequence
            .dropWhile { it.commitSha != lastProductionSha }
}

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val mrs = fetchMergeRequestsSequence(GitLabService(), "04d78f5c7cd51c52d0482d08224ff6a214da12c1").take(5)
        println(mrs)
        println(mrs.toList().size)
    }

    println(time)
}