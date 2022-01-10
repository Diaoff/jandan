# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER jandan

# 挂载目录
VOLUME /home/jandan
# 创建目录
RUN mkdir -p /home/jandan/target
RUN mkdir -p /home/jandan/logs
# 指定路径
WORKDIR /home/jandan
# 复制jar文件到路径
COPY ./target/jandan.jar /home/jandan/target/jandan.jar
COPY ry.sh /home/jandan/ry.sh
# 启动服务
ENTRYPOINT ["java","-jar","target/jandan.jar","-Dname=jandan","-Duser.timezone=Asia/Shanghai","-Xms512M","-Xmx512M","-XX:PermSize=256M","-XX:MaxPermSize=512M","-XX:+HeapDumpOnOutOfMemoryError","-XX:+PrintGCDateStamps ","-XX:+PrintGCDetails","-XX:NewRatio=1","-XX:SurvivorRatio=30","-XX:+UseParallelGC","-XX:+UseParallelOldGC"]
