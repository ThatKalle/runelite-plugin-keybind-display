#!/bin/bash
set -e

while [[ "$#" -gt 0 ]]; do
    case $1 in
        --basename) 
            BASENAME="$2"
            shift
            ;;
        *) echo "Unknown parameter passed: $1"; exit 1 ;;
    esac
    shift
done

DATADIRECTORYNAME=$(jq -r '.dataDirectoryName' /opt/idea/product-info.json)
IDEA_FOLDER="/home/vscode/.config/JetBrains/${DATADIRECTORYNAME}"
mkdir -p "${IDEA_FOLDER}/options"

cat > "${IDEA_FOLDER}/options/trusted-paths.xml" <<EOF
<application>
  <component name="Trusted.Paths">
    <option name="TRUSTED_PROJECT_PATHS">
      <map>
        <entry key="/workspaces/../${BASENAME}" value="true" />
        <entry key="/workspaces/${BASENAME}" value="true" />
      </map>
    </option>
  </component>
  <component name="Trusted.Paths.Settings">
    <option name="TRUSTED_PATHS">
      <list>
        <option value="/workspaces" />
      </list>
    </option>
  </component>
</application>
EOF

cat > "${IDEA_FOLDER}/options/AIOnboardingPromoWindowAdvisor.xml" <<EOF
<application>
  <component name="AIOnboardingPromoWindowAdvisor">
    <option name="attempts" value="3" />
    <option name="shouldShowNextTime" value="NO" />
  </component>
</application>
EOF

sudo chown -R vscode /workspaces
