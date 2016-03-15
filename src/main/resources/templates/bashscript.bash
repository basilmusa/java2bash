#!/bin/bash
set -euo pipefail

########## COMMON INCLUDES #################################################
{{uniqueCode}}

########## CODE ############################################################
{{code}}

########## CLEANUP CODE ####################################################
function finish_cleanup {
{{cleanupCode}}
}
trap finish_cleanup EXIT
