#!/bin/bash
#########################################################
#
# To run it as a SHORTCUTS, do:
#   1. create file.sh
#   2. run: chmod +x file.sh
#   3. add as shortcut:
#     - "/bin/bash /...path here.../file.sh"
#
#########################################################
#
#
#
#
# the code is written by steeve.mccauley from: https://extensions.gnome.org/extension/935/toggle-touchpad/
# I tested on Gnome 3.38.2 on 2020-12-17. It works perfectly.
# Then added a keyboard shortcut to easy run the script to turn on or off the laptop touchpad.
class=org.gnome.desktop.peripherals.touchpad #location in gconf settings where the touchpad is en/disabled
name=send-events #name of the actual setting
status=$(gsettings get "$class" "$name")
status=${status,,} # normalize to lower case; this is a modern bash extension

echo Current status is $status
if [[ $status = "'disabled'" ]]; then # needs " ' '" to work
echo it is off
new_status=enabled # ' not required
notify-send "Touchpad" ">>> ENABLED <<<"
else
new_status=disabled
notify-send "Touchpad" ">>> DISABLED <<<"
fi
echo "Toggling to $new_status"
gsettings set "$class" "$name" "$new_status"

