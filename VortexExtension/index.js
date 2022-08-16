//Import some assets from Vortex we'll need.
const path = require('path');
const { fs, log, util } = require('vortex-api');

// Nexus Mods domain for the game. e.g. nexusmods.com/bloodstainedritualofthenight
const GAME_ID = 'wildermyth';
const STEAMAPP_ID = '763890';
const GOGAPP_ID = '1853330157';

function main(context) {
	//This is the main function Vortex will run when detecting the game extension. 
	
	return true
}

module.exports = {
    default: main,
  };