import rat.poison.settings.*

///////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// --- GENERAL --- ///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Enables the flat aim script.
 *
 * This script uses traditional flat linear-regression smoothing.
 */
SMG_ENABLE_FLAT_AIM = false

/**
 * Enables the path aim script.
 *
 * This script uses an advanced path generation smoothing.
 */
SMG_ENABLE_PATH_AIM = true

/**
 * Whether or not to account for recoil when aiming
 */
SMG_FACTOR_RECOIL = true

/**
 * Default aim bone the aims go to
 */
SMG_AIM_BONE = 6

/**
 * The field of view of the aimbot, in degrees (0 to 360).
 */
SMG_AIM_FOV = 40

/**
 * The aimbot's "playback" speed, the higher the value the slower the playback.
 *
 * The minimum value is 1
 */
SMG_AIM_SPEED = 24

/**
 * The smoothness of the aimbot
 */
SMG_AIM_SMOOTHNESS = 1.2

/**
 * The strictness, or "stickiness" of the aimbot; the higher the number, the
 * less strict the aimbot will stick to targets.
 *
 * The minimum value is 1.0
 */
SMG_AIM_STRICTNESS = 1.0


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// --- PERFECT AIM --- /////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Whether or not to use perfect aim, which will instantaneously snap
 * to the aim bone once you are within the [PERFECT_AIM_FOV].
 */
SMG_PERFECT_AIM = false

/**
 * The FOV, in degrees (0 to 360) to snap for perfect aim.
 */
SMG_PERFECT_AIM_FOV = 40

/**
 * The chance, from 1% to 100% (0 to 100) for perfect aim to activate.
 */
SMG_PERFECT_AIM_CHANCE = 100


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// --- AIM ASSIST --- //////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Enables "aim assist" mode, which has no stickiness, and gives you small
 * extra movements towards the aim bone.
 *
 * This setting should be used by high-level players who are experienced aimers.
 */
SMG_AIM_ASSIST_MODE = true

/**
 * The amount of strictness for the aim assist mode, with a mimimum value of 1.
 */
SMG_AIM_ASSIST_STRICTNESS = 80
