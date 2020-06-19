package rat.poison.ui.tabs

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.tabbedpane.Tab
import rat.poison.curLocalization
import rat.poison.curSettings
import rat.poison.settings.*
import rat.poison.ui.uiHelpers.tables.AimBTrigTable
import rat.poison.ui.uiHelpers.tables.AimBacktrackTable
import rat.poison.ui.uiHelpers.tables.AimTable
import rat.poison.ui.uiPanels.aimTab
import rat.poison.utils.varUtil.strToBool

//I really couldn't give a shit to update this to the same as the other tabs

var categorySelected = curSettings["DEFAULT_CATEGORY_SELECTED"]

class AimTab : Tab(true, false) { //Aim.kts tab
    private val table = VisTable(false)
    val tAim = AimTable()
    val tTrig = AimBTrigTable()
    val tBacktrack = AimBacktrackTable()

    init {
        table.add(tAim).growX().row()
        table.add(tTrig).growX().row()
        table.add(tBacktrack).growX()
    }

    override fun getContentTable(): Table? {
        return table
    }

    override fun getTabTitle(): String? {
        return curLocalization["AIM_TAB_NAME"]
    }
}

fun updateDisableAim() {
    aimTab.tAim.apply {
        val bool = !enableAim.isChecked
        var col = Color(255F, 255F, 255F, 1F)
        if (bool) {
            col = Color(105F, 105F, 105F, .2F)
        }
        if (bool) {
            weaponOverrideCheckBox.isChecked = !bool
        } else {
            weaponOverrideCheckBox.isChecked = curSettings["ENABLE_OVERRIDE"].strToBool()
        }
        weaponOverrideCheckBox.isDisabled = bool
        activateFromFireKey.disable(bool)
        teammatesAreEnemies.disable(bool)
        holdAim.disable(bool)
        automaticWeaponsCheckBox.disable(bool)
        targetSwapDelay.disable(bool, col)

        fovTypeLabel.color = col
        fovTypeBox.isDisabled = bool

        forceAimBoneKey.disable(bool, col)
        forceAimKey.disable(bool, col)
        forceAimAlways.disable(bool)
        forceAimThroughWalls.disable(bool)
        categorySelectLabel.color = col
        categorySelectionBox.isDisabled = bool
        enableAimOnShot.isDisabled = bool
        enableFactorRecoil.isDisabled = bool
        enableFlatAim.isDisabled = bool
        enablePathAim.isDisabled = bool
        enableScopedOnly.isDisabled = bool
        aimBoneLabel.color = col
        aimBoneBox.isDisabled = bool
        forceAimBoneLabel.color = col
        forceAimBoneBox.isDisabled = bool
        aimFov.disable(bool, col)
        aimSpeed.disable(bool, col)
        aimSmooth.disable(bool, col)
        if (!aimAfterShots.isDisabled()) {
            aimAfterShots.disable(bool, col)
        }
        perfectAimCollapsible.isCollapsed = !perfectAimCheckBox.isChecked
        perfectAimCheckBox.isDisabled = bool
        perfectAimChance.disable(bool, col)
        perfectAimFov.disable(bool, col)

        randomizeX.disable(bool, col)
        randomizeY.disable(bool, col)
        randomizeDZ.disable(bool, col)
        advancedSettingsCollapsible.isCollapsed = !advancedSettingsCheckBox.isChecked
        advancedSettingsCheckBox.isDisabled = bool
        advancedRcsX.disable(bool, col)
        advancedRcsY.disable(bool, col)
        advancedRcsVariation.disable(bool, col)
        advancedSpeedDivisor.disable(bool, col)
    }
}

fun updateAim() {
    aimTab.tAim.apply {
        enableAim.update()
        updateMap()
        activateFromFireKey.update()
        teammatesAreEnemies.update()
        holdAim.update()
        fovTypeLabel.update()
        aimBoneLabel.update()
        perfectAimFov.update()
        weaponOverrideCheckBox.update()
        forceAimBoneLabel.update()
        perfectAimChance.update()
        perfectAimCheckBox.update()
        advancedSettingsCheckBox.update()
        aimBoneBox.setItems(curLocalization["HEAD"], curLocalization["NECK"], curLocalization["CHEST"], curLocalization["STOMACH"], curLocalization["NEAREST"], curLocalization["RANDOM"])
        fovTypeBox.setItems(curLocalization["STATIC"], curLocalization["DISTANCE"])
        forceAimBoneBox.setItems(curLocalization["HEAD"], curLocalization["NECK"], curLocalization["CHEST"], curLocalization["STOMACH"], curLocalization["NEAREST"], curLocalization["RANDOM"])
        categorySelectionBox.setItems(curLocalization["PISTOL"], curLocalization["RIFLE"], curLocalization["SMG"], curLocalization["SNIPER"], curLocalization["SHOTGUN"])
        forceAimBoneKey.update()
        forceAimKey.update()
        forceAimAlways.update()
        forceAimThroughWalls.update()
        automaticWeaponsCheckBox.update()
        targetSwapDelay.update()

        enableAimOnShot.update()
        enableFactorRecoil.update()
        enableFlatAim.update()
        enablePathAim.update()

        enableScopedOnly.update()
        if (categorySelected == "SNIPER") {
            enableScopedOnly.color = Color(255F, 255F, 255F, 1F)
            enableScopedOnly.isDisabled = false
        } else {
            enableScopedOnly.color = Color(255F, 255F, 255F, 0F)
            enableScopedOnly.isDisabled = true
        }

        aimAfterShots.update()
        if (categorySelected == "RIFLE" || categorySelected == "SMG") {
            aimAfterShots.disable(false, Color(255F, 255F, 255F, 1F))
        } else {
            aimAfterShots.disable(true, Color(0F, 0F, 0F, 0F))
        }

        fovTypeBox.selected = when(curSettings["FOV_TYPE"]) {
            "DISTANCE" -> curLocalization["DISTANCE"]
            else -> curLocalization["STATIC"]
        }

        aimBoneBox.selected = when (curSettings[categorySelected + "_AIM_BONE"].toInt()) {
            HEAD_BONE -> curLocalization["HEAD"]
            NECK_BONE -> curLocalization["NECK"]
            CHEST_BONE -> curLocalization["CHEST"]
            STOMACH_BONE -> curLocalization["STOMACH"]
            NEAREST_BONE -> curLocalization["NEAREST"]
            else -> curLocalization["RANDOM"]
        }

        forceAimBoneBox.selected = when (curSettings[categorySelected + "_AIM_FORCE_BONE"].toInt()) {
            HEAD_BONE -> curLocalization["HEAD"]
            NECK_BONE -> curLocalization["NECK"]
            CHEST_BONE -> curLocalization["CHEST"]
            STOMACH_BONE -> curLocalization["STOMACH"]
            NEAREST_BONE -> curLocalization["NEAREST"]
            else -> curLocalization["RANDOM"]
        }

        aimFov.update()
        aimSpeed.update()
        aimSmooth.update()
        aimAfterShots.update()
        perfectAimCheckBox.isChecked = curSettings[categorySelected + "_PERFECT_AIM"].strToBool()
        perfectAimCollapsible.isCollapsed = !curSettings[categorySelected + "_PERFECT_AIM"].strToBool()
        perfectAimFov.update()
        perfectAimChance.update()

        randomizeX.update()
        randomizeY.update()
        randomizeDZ.update()
        advancedSettingsCheckBox.isChecked = curSettings[categorySelected + "_ADVANCED_SETTINGS"].strToBool()
        advancedSettingsCollapsible.isCollapsed = !curSettings[categorySelected + "_ADVANCED_SETTINGS"].strToBool()
        advancedRcsX.update()
        advancedRcsY.update()
        advancedRcsVariation.update()
        advancedSpeedDivisor.update()

        updateDisableAim()
    }
}

//Triggerbot
fun updateDisableTrig() {
    aimTab.tTrig.apply {
        val bool = if (!aimTab.tAim.enableAim.isChecked) { //Issue?
            enableTrig.disable(true)
            !enableTrig.isChecked
        } else {
            enableTrig.disable(false)
            !enableTrig.isChecked
        }
        var col = Color(255F, 255F, 255F, 1F)
        if (bool) {
            col = Color(105F, 105F, 105F, .2F)
        }
        categorySelectLabel.color = col
        categorySelectionBox.isDisabled = bool
        if (!aimTab.tAim.enableAim.isChecked) {
            trigAimbot.disable(true)
        } else {
            trigAimbot.disable(bool)
        }
        trigInCross.disable(bool)
        trigInFov.disable(bool)
        if (!trigInFov.isChecked) {
            trigFov.disable(true, Color(105F, 105F, 105F, .2F))
        } else {
            trigFov.disable(bool, col)
        }
        trigDelay.disable(bool, col)
    }
}

fun updateTrig() {
    aimTab.tTrig.apply {
        enableTrig.update()
        enableTrig.update()
        trigInCross.update()
        trigInFov.update()
        trigFov.update()
        trigAimbot.update()
        trigDelay.update()
    }

    updateDisableTrig()
}

//Backtrack
fun updateDisableBacktrack() {
    aimTab.tBacktrack.apply {
        val bool = !aimTab.tBacktrack.enableBacktrack.isChecked

        var col = Color(255F, 255F, 255F, 1F)
        if (bool) {
            col = Color(105F, 105F, 105F, .2F)
        }

        //enableBacktrack.disable(bool)
        backtrackVisualize.disable(bool)
        backtrackFOV.disable(bool, col)
        backtrackMS.disable(bool, col)
        backtrackSpotted.disable(bool)
        backtrackPreferAccurate.disable(bool)
        categorySelectLabel.color = col
        categorySelectionBox.isDisabled = bool
        backtrackWeaponEnabled.disable(bool)
        backtrackWeaponNeck.disable(bool)
        backtrackWeaponChest.disable(bool)
        backtrackWeaponStomach.disable(bool)
        backtrackWeaponPelvis.disable(bool)
    }
}

fun updateBacktrack() {
    aimTab.tBacktrack.apply {
        updateMap()
        enableBacktrack.update()
        bonesVisLabel.update()
        backtrackVisualize.update()
        backtrackFOV.update()
        backtrackMS.update()
        backtrackPreferAccurate.update()
        backtrackSpotted.update()
        backtrackWeaponEnabled.update()
        backtrackWeaponNeck.update()
        backtrackWeaponChest.update()
        backtrackWeaponStomach.update()
        backtrackWeaponPelvis.update()
    }

    updateDisableBacktrack()
}
