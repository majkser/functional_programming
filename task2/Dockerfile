FROM ubuntu:24.04

RUN apt-get update

# install Java 8
RUN apt-get update && apt-get install -y openjdk-8-jdk

# install Python 3
RUN apt-get update && apt-get install -y python3

# install vim
RUN apt-get update && apt-get install -y vim

# install scala
RUN apt-get update && apt-get install -y scala

RUN apt-get update && apt-get install -y curl gnupg

# install sbt
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | gpg --dearmor -o /etc/apt/trusted.gpg.d/sbt.gpg
RUN apt-get update && apt-get install -y sbt