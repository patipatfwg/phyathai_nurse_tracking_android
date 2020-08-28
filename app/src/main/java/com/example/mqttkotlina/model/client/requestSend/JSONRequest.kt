package com.freewill.phayathaidetect.model.requestSend

import com.fg.mdp.fwgfacilitiesfinder.model.responsSend.Androidbox
import com.fg.mdp.fwgfacilitiesfinder.model.responsSend.iTAG
import com.freewill.phayathaidetect.model.responeMessage.Body
import com.freewill.phayathaidetect.model.responeMessage.Head

data class JSONRequest(
    val androidbox:Androidbox,
    val iTAG: iTAG
)