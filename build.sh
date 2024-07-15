# shellcheck disable=SC2162

# set variables
echo -ne "Enter release version: "
read RELEASE
# maven
mvn clean
mvn install
mvn package
# docker
docker build -t proxyapi-java:latest -t proxyapi-java:"$RELEASE" .
docker tag proxyapi-java:"$RELEASE" alexeyzolotarev2077/proxyapi-java:"$RELEASE"
docker push alexeyzolotarev2077/proxyapi-java:"$RELEASE"
