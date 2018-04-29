package pagination

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.channels.takeWhile
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

fun fetchMergeRequestsChannel(gitLabService: GitLabService, lastProductionSha: String): ReceiveChannel<MergeRequest> {
    return produce {
        var page = 1
        while (true) {
            gitLabService.delayedFetchMergeRequests(page++).forEach { send(it) }
        }
    }.takeWhile { it.commitSha != lastProductionSha }
}


fun fetchMergeRequestsChannel2(gitLabService: GitLabService, lastProductionSha: String): ReceiveChannel<MergeRequest> {
    return produce {
        var page = 1
        while (true) {
            gitLabService.fetchMergeRequests(page++).forEach {
                println("Sending $it")
                send(it)
            }
        }
    }
}

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val mrs = fetchMergeRequestsChannel(GitLabService(), "04d78f5c7cd51c52d0482d08224ff6a214da12c1")

        runBlocking {
            println("Start consuming")
            delay(10, TimeUnit.SECONDS)
            mrs.consumeEach {
                println("consuming $it")
            }
        }
    }

    println(time)
}