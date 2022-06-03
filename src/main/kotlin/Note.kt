data class Note(

    var title: String,
    var text: String,
    var privacy: Int,
    var commentPrivacy: Int,
    var privacyView: String,
    var privacyComment: String,
    var noteId: Int,
    var userId: Int,
    var offset: Int,
    var count: Int,
    var sort: Boolean,
    var ownerId: Int,
    var needWiki: Boolean

)