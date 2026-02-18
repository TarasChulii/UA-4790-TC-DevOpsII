#!/bin/bash 

declare -l STDIN_DATA
echo ""
read -p 'Please select which package manager you would like to use NPM or YARN:> ' STDIN_DATA

START_TIME=$SECONDS
#echo $START_TIME

COMMAND_STR="$STDIN_DATA install > /dev/null 2>&1"
#echo $COMMAND_STR
eval $COMMAND_STR

END_TIME=$SECONDS
#echo $END_TIME

ELAPSED=$(( END_TIME - START_TIME ))

RED='\033[1;31m'
GREEN='\033[1;32m'
NC='\033[0m' # No Color (Reset)
echo ""
echo -e "Execution time for the command ${RED}'$COMMAND_STR'${NC} took  ${GREEN}$ELAPSED${NC} seconds"
echo ""




