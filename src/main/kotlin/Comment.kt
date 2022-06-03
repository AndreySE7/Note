data class Comment(

    var noteId: Int,
    var ownerId: Int,
    var replyTo: Int,
    var message: String,
    var guid: Int,
    var commentId: Int,
    var offset: Int,
    var sort: Boolean,
    var count: Int,
    var deleted: Boolean = false

)