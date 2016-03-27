#!/bin/bash
set -euo pipefail

########## COMMON INCLUDES #################################################
{{uniqueCode}}

########## CODE ############################################################
{{code}}

{% if cleanupCode is not empty %}
########## CLEANUP CODE ####################################################
function finish_cleanup {
{{cleanupCode}}
}
trap finish_cleanup EXIT
{% endif %}
