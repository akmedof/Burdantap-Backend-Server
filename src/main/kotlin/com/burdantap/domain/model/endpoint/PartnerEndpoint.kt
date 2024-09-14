package com.burdantap.domain.model.endpoint

sealed class PartnerEndpoint(val path: String) {
    data object Create: PartnerEndpoint("/partner/create")
    data object Register: PartnerEndpoint("/partner/register")
    data object Update: PartnerEndpoint("/partner/update")
    data object Delete: PartnerEndpoint("/partner/delete")
    data object List: PartnerEndpoint("/partner/list")
    data object Find: PartnerEndpoint("/partner/find")
    data object Read: PartnerEndpoint("/partner/read")
}