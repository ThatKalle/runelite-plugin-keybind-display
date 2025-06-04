# https://mcr.microsoft.com/en-us/artifact/mar/devcontainers/base/tags
FROM mcr.microsoft.com/devcontainers/base:1.0.23-bookworm@sha256:da67c59f82e057e0ccb81ec7d13fb50464c26f477638018e6de1b2623a008a3a AS setup

USER root

ARG IDEA_VERSION \
    IDEA_CHECKSUM
ENV IDEA_URL="https://download.jetbrains.com/idea/ideaIC-${IDEA_VERSION}.tar.gz"

RUN <<EOF
export DEBIAN_FRONTEND=noninteractive && apt-get update 
apt-get upgrade -y
apt-get -y install --no-install-recommends \
ca-certificates coreutils curl
EOF

RUN <<EOF
FILE="idea.tar.gz"
SHAFILE="${FILE}.sha256"
curl -L -o ${FILE} ${IDEA_URL}
echo "${IDEA_CHECKSUM}  ${FILE}" > "${SHAFILE}"
sha256sum -c "${SHAFILE}"
mkdir -p /tmp/idea
tar -xzf idea.tar.gz -C /tmp/idea/
mv /tmp/idea/* /opt/idea
EOF

# https://mcr.microsoft.com/en-us/artifact/mar/devcontainers/base/tags
FROM mcr.microsoft.com/devcontainers/base:1.0.23-bookworm@sha256:da67c59f82e057e0ccb81ec7d13fb50464c26f477638018e6de1b2623a008a3a

ARG JAVA_VERSION

ENV PATH="/opt/idea/bin:$PATH"

USER root

COPY --from=setup /opt/idea /opt/idea

RUN <<EOF
export DEBIAN_FRONTEND=noninteractive && apt-get update 
apt-get upgrade -y
apt-get -y install --no-install-recommends \
ca-certificates coreutils curl git gnupg2 gpg wget zip
curl -fsSL https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor -o /etc/apt/keyrings/adoptium.gpg
chmod a+r /etc/apt/keyrings/adoptium.gpg
echo "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/adoptium.gpg] https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list
apt-get update
apt-get -y install ${JAVA_VERSION}
apt-get clean 
rm -rf /var/lib/apt/lists/* 
rm -rf /var/cache/apt/archives/partial/*
EOF

USER vscode

HEALTHCHECK NONE

