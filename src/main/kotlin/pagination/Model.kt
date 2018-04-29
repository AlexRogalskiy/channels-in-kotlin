package pagination

import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit

data class MergeRequest(val commitSha: String)

class GitLabService {

    val shaList = listOf(
            "04d7106176c67aa7487bc69faf44276a93f8ef9d",
            "04d72bc80c56a84432d51106ecdc6ae29de39b86",
            "04d72f49b76422640f2d03475ef8413924b7e45b",
            "04d7361a34995de433c67cd96acaccfbc8183f73",
            "04d74d00719e94077a5b40356c7a5711c85af935",
            "04d758f283231e6cab3dd62a2f09ab42a6bedb39",
            "04d759dacc74b74f05e60e1dc878dee5b65f4104",
            "04d75b52e0d9ba8f47db024eb3827a7d15318f04",
            "04d76605b81b8f2b1f1f1749bd3f81078e664a64",
            "04d7666fbd633f66da47f85de974cac86c3442c0",
            "04d769f697e387332f0cd025ab567de9dd08e6f7",
            "04d76a33dda050605f39997a558fd6126e103e7d",
            "04d76c8160266abe2b8a1b5b5ae90bb860bf0094",
            "04d773484d03ed5418d6b6f14a92496a29c85532",
            "04d77fe7468a76b512bbb0771794eb9f6de31c01",
            "04d780e2786f16e0f4781f0be2f40a301667cca6",
            "04d78804f895b2d9bccb96c6c604e990c2b768b9",
            "04d78f5c7cd51c52d0482d08224ff6a214da12c1",
            "04d78fe7121d0c3eee2f9eeb114ab2dbed3902f3",
            "04d7938f9a7ab04b851726a71296bcc144bf1913",
            "04d7a02910b4e7a15447f1628d676d1b420547a1",
            "04d7a4f28a2c1963d325b120620c2d7c6daa7b22",
            "04d7a9a572b8032a7bd55647ca19b4b8ef00134d",
            "04d7ad07e08b41e963bceaf3d185a32b574323b9",
            "04d7af1569d59281239c32d456efc8ba5d483eb4",
            "04d7b625c0e5375d333df3ff0dad399d3da90c11",
            "04d7b8ecaaa8a6fbca966b9080bb65edf0722ae6",
            "04d7c01601b62b262963ac3ef371828ac89abf63",
            "04d7c09e935b0dc547493cd3b8781bf923c92946",
            "04d7c27d25751e0121a66a5af949a9b9ba26fd39",
            "04d7c2facbfe0521c6459c728b3743a44c522058",
            "04d7c4e54190fde9132eee0dafd27f754f8f2497",
            "04d7c6b855591236527635095b844dc904c94d01",
            "04d7d16fae56995aa52be0f3c546c0085d5dbaf7",
            "04d7d58eab58d9990e06b3bf4d9f8f90bedcc4c0",
            "04d7d97a7b7afb8699e1da00845be8505dc2d474",
            "04d7dd60ed188aa272cbca7cecc7fab02fe1ca6c",
            "04d7e999fc1c6e5daba8762c93512931686cafca",
            "04d7ea3cba18e5b2b2bbde0294a457d07dac5fbb",
            "04d7ecdd9bcd482712a7ec6afb3c9125b7fff160",
            "04d7f72c91d19c9eeef3261d51b18322c0c9ff58",
            "04d807e571e19c5b4fe5a418c8af184cffea9ee3",
            "04d80958b085433dcc773cee222320dd13098a38",
            "04d80e409839e63f5fbd47042fae00703ec88d7d",
            "04d8261a9dafb476ca6d5a10af976c34ae488cac",
            "04d83e698afbd8ba34e3f5c7ba56720f8454d342",
            "04d84347eb9b6037d9dc757d3a3cdec8e0d3e344",
            "04d84dad49a8f3d1a97019a4317cf8ddac5b13dc",
            "04d850b42c18867d16a0b46c6caabf7ade3984bb",
            "04d85b21f7b852b77524947a345986f82d91934d",
            "04d861c3b6e1c39fffe286b824e099670f5ad2bb",
            "04d86c3af9e3cc5c2ba4545662e25b2dd1adc3b2",
            "04d86ea80505f58be0b9ff211b6484ca9b0b7a90",
            "04d873293bd4699091b3fccfbd950e92497dc975",
            "04d8818772a23a718eba1d6c76be3c33154ffa63"
    )

    fun fetchMergeRequests(page: Int): List<MergeRequest> {
        println("Start fetching MRs")
        val perPage = 5
        val result = shaList.subList(page * perPage, (page + 1) * perPage).map { MergeRequest(it) }
        println("End fetching MRs")
        return result
    }

    suspend fun delayedFetchMergeRequests(page: Int): List<MergeRequest> {
        delay(10, TimeUnit.SECONDS)
        return fetchMergeRequests(page)
    }
}

fun main(args: Array<String>) {
    println(GitLabService().shaList.size)
}