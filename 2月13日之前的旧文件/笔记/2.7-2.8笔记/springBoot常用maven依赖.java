1��maven-compiler-plugin
<build>
   <plugins>
      <!-- ָ��maven�����jdk�汾,�����ָ��,maven3Ĭ����jdk 1.5 maven2Ĭ����jdk1.3 -->                                                                           
    <groupId>org.apache.maven.plugins</groupId>                                                                                               
    <artifactId>maven-compiler-plugin</artifactId>                                                                                            
    <version>3.1</version>                                                                                                                    
    <configuration>                                                                                                                           
        <!-- һ����ԣ�target��source�Ǳ���һ�µģ����ǣ���ʱ��Ϊ���ó������������汾��jdk������(���ڵͰ汾Ŀ��jdk��Դ�����в���ʹ�õͰ汾jdk�в�֧�ֵ��﷨)�������target��ͬ��source����� -->                    
        <source>1.8</source> <!-- Դ����ʹ�õ�JDK�汾 -->                                                                                             
        <target>1.8</target> <!-- ��Ҫ���ɵ�Ŀ��class�ļ��ı���汾 -->                                                                                     
        <encoding>UTF-8</encoding><!-- �ַ������� -->
        <skipTests>true</skipTests><!-- �������� -->                                                                             
        <verbose>true</verbose>
        <showWarnings>true</showWarnings>                                                                                                               
        <fork>true</fork><!-- ҪʹcompilerVersion��ǩ��Ч������Ҫ��fork��Ϊtrue��������ȷ��ʾ����汾���õĿ��� -->                                                                     
    </configuration>                         
    </plugins>
</build>
2��spring-boot-maven-plugin
Spring Boot Maven plugin�ܹ���Spring BootӦ�ô��Ϊ��ִ�е�jar��war�ļ���Ȼ����ͨ���ķ�ʽ����Spring BootӦ�á�
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.0.1.RELEASE</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
	
<dependencies>
        <!--�������ݿ�-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!-- Druid ���ݿ����ӳ� -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.5</version>
        </dependency>
           <!--mysql����-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!-- MyBatis ��ҳ��� -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.10</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--�����ļ�xml��properties������-->
        <dependency>
            <groupId> org.springframework.boot </groupId>
            <artifactId> spring-boot-configuration-processor </artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>