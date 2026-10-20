package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Status

data class StatusModel(
    val statusId: Int,
    val count: Int,
    val status: String
) {
    companion object {
        fun from(status: Status): StatusModel = StatusModel(
            statusId = status.statusId.toIntOrNull() ?: 0,
            count = status.count.toIntOrNull() ?: 0,
            status = status.status
        )
    }
}
