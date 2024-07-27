# shellcheck disable=SC2162
# shellcheck disable=SC2140
# shellcheck disable=SC2016
# shellcheck disable=SC2094
# shellcheck disable=SC2094

# set variables
echo -ne "Enter new release version: v"
read RELEASE
RELEASE="v$RELEASE"
echo -ne "Enter previous release version: v"
read PREV_RELEASE
PREV_RELEASE="v$PREV_RELEASE"

#edit configuration files
sed "s|$PREV_RELEASE|$RELEASE|" pom.xml > temp_pom.xml && mv temp_pom.xml pom.xml
sed "s|$PREV_RELEASE|$RELEASE|" Dockerfile > temp_Dockerfile && mv temp_Dockerfile Dockerfile

# maven
mvn clean
mvn install
mvn package

# docker
docker build --platform linux/amd64 -t proxyapi-java:latest -t proxyapi-java:"$RELEASE" .
docker tag proxyapi-java:"$RELEASE" alexeyzolotarev2077/proxyapi-java:"$RELEASE"
docker push alexeyzolotarev2077/proxyapi-java:"$RELEASE"

#run

#MacOS
#docker run -d --platform linux/amd64 -p 8080:22870 alexeyzolotarev2077/proxyapi-java:latest