FROM maven

WORKDIR /usr/app

COPY . .
RUN mvn install

CMD ["bash"]