package rat.poison.ui.uiHelpers.aimTab

import com.kotcrab.vis.ui.widget.Tooltip
import com.kotcrab.vis.ui.widget.VisCheckBox
import rat.poison.curLocalization
import rat.poison.curSettings
import rat.poison.ui.changed
import rat.poison.ui.tabs.categorySelected
import rat.poison.ui.tabs.updateDisableAim
import rat.poison.ui.uiUpdate
import rat.poison.utils.varUtil.boolToStr
import rat.poison.utils.varUtil.strToBool

class ATabVisCheckBox(mainText: String, varExtension: String, nameInLocalization: String = "") : VisCheckBox(mainText) {
    private val defaultText = mainText
    private val variableExtension = varExtension
    private val localeName = nameInLocalization

    init {
        update()
        if (curLocalization[nameInLocalization+"_TOOLTIP"] != "") {
            Tooltip.Builder(curLocalization[nameInLocalization+"_TOOLTIP"]).target(this).build()
        }
        changed { _, _ ->
            curSettings[categorySelected + variableExtension] = isChecked.boolToStr()

            //Custom checks
            if (isChecked) {
                if (variableExtension == "_ENABLE_FLAT_AIM") {
                    curSettings[categorySelected + "_ENABLE_PATH_AIM"] = "false"
                } else if (variableExtension == "_ENABLE_PATH_AIM") {
                    curSettings[categorySelected + "_ENABLE_FLAT_AIM"] = "false"
                }
            }
            uiUpdate()
            updateDisableAim()
            true
        }
    }

    fun update() {
        var tmpText = curSettings[categorySelected + variableExtension]

        if (tmpText.isNotEmpty()) {
            isChecked = tmpText.strToBool()
        } else {
            println("[Error] $categorySelected$variableExtension is empty")
        }

        tmpText = curLocalization[localeName]
        this.setText(if (tmpText.isBlank()) defaultText else tmpText )
    }

    fun disable(bool: Boolean) {
        isDisabled = bool
    }
}