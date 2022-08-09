# Wildermyth Patch Builder

Tool that currently merges any human.names.json files from any workshop subscribed mods you have. It does this by creating a 'user mod' with a mashed together json file. As long as that file is loaded in a higher priority, you'll have any additions to the human.names.json instead of only having the additions from one of the files.

## Running

Once you've downloaded the jar, you can run it in command line or create a bat file. You'll need to give it the location of your install and workshop folder, if they're not in the default location. Your run script should look something like this:

```
java -jar patch-builder.jar C:/Program Files/Steam/steamapps/common/Wildermyth/mods C:/Program Files/Steam/steamapps/workshop/content/763890
```