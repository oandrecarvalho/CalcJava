# 📘 Projeto Calculadora em Java (Design Patterns + Testes + Jacoco + SonarQube)

Este projeto demonstra uma implementação simples e elegante de uma **Calculadora Modular em Java**, utilizando **Design Patterns (Strategy)**, princípios de **Clean Code**, testes automatizados com **JUnit 5**, relatório de **Code Coverage com Jacoco**, e análise de qualidade com **SonarQube**.

O objetivo é fornecer um exemplo completo, fácil de entender e totalmente configurado para ser avaliado em ferramentas de qualidade de código.

---

# 🛠️ 1. Requisitos do Ambiente

### ✔ Java JDK 17 ou superior

### ✔ Maven 3.8+

### ✔ SonarQube Community Edition 10+

### ✔ Git (opcional)

---

# 🚀 2. Instalando Dependências do Ambiente

## **2.1 Verificar/Instalar Java**

### Fedora

```
sudo dnf install java-17-openjdk java-17-openjdk-devel
```

Verificar:

```
java -version
```

---

## **2.2 Instalar Maven**

### Fedora

```
sudo dnf install maven
```

Verificar:

```
mvn -version
```

---

# 📁 3. Estrutura do Projeto

```
src/
 ├── main/java/calculator/
 │     ├── Operation.java
 │     ├── Sum.java
 │     ├── Subtraction.java
 │     ├── Mult.java
 │     ├── Division.java
 │     ├── Calculator.java
 │     └── App.java
 └── test/java/calculator/
       ├── OperationTests.java
       └── CalculatorTest.java
```

---

# 📦 4. Arquivo pom.xml (Configuração Completa)

O projeto utiliza:

* **JUnit 5** para testes
* **Jacoco** para code coverage
* **SonarQube Scanner** via Maven

Certifique-se de que seu `pom.xml` contém:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>calculator</groupId>
    <artifactId>calculator</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>

            <!-- JUnit 5 Runner -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.2</version>
                <configuration>
                    <useModulePath>false</useModulePath>
                </configuration>
            </plugin>

            <!-- Jacoco para Code Coverage -->
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.11</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- SonarQube Scanner -->
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.11.0.3922</version>
            </plugin>

        </plugins>
    </build>
</project>
```

---

# 🧪 5. Executando Testes

---

# 🐳 5.1 Configuração do SonarQube Usando Podman (Containers)

Você pode executar o **SonarQube** usando **Podman**, sem precisar instalar nada na sua máquina além do container runtime.

## 👉 1. Baixar a imagem do SonarQube

```
podman pull docker.io/library/sonarqube:latest
```

## 👉 2. Executar o container do SonarQube

```
podman run -d \
  --name sonarqube \
  -p 9000:9000 \
  -v sonarqube_data:/opt/sonarqube/data \
  -v sonarqube_extensions:/opt/sonarqube/extensions \
  docker.io/library/sonarqube:latest
```

### ✔ O SonarQube iniciará em:

```
http://localhost:9000
```

### ✔ Login padrão:

* **user:** admin
* **password:** admin

Depois do primeiro login, gere o token em:

```
Account → Security → Generate Token
```

## 👉 3. Verificar logs (opcional)

```
podman logs -f sonarqube
```

## 👉 4. Parar o container

```
podman stop sonarqube
```

## 👉 5. Iniciar novamente

```
podman start sonarqube
```

---

Rodar todos os testes JUnit:

```
mvn test
```

Rodar testes + cobertura Jacoco:

```
mvn clean verify
```

Relatório HTML do Jacoco:

```
target/site/jacoco/index.html
```

---

# 📊 6. Instalando e Rodando o SonarQube Local

## 6.1 Baixar SonarQube

```
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-10.5.0.89927.zip
unzip sonarqube-10.5.0.89927.zip
```

## 6.2 Iniciar SonarQube

```
cd sonarqube-10.5.0.89927/bin/linux-x86-64/
./sonar.sh start
```

Acessar no navegador:

```
http://localhost:9000
```

### Login padrão:

* **user:** admin
* **password:** admin

Após login, gere um **token** em:

```
Account > Security > Generate Tokens
```

---

# 🛰️ 7. Rodando o SonarQube com Maven e Token

Execute:

```
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=calculadora \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=SEU_TOKEN_AQUI
```

Após isso, abra:

```
http://localhost:9000/projects
```

Você verá:
✔ Code Smells
✔ Bugs
✔ Cobertura Jacoco
✔ Qualidade do código
✔ Duplicações

---

# ✔️ 8. Como Rodar a Aplicação

```
mvn clean package
java -cp target/Calculator-1.0-SNAPSHOT.jar calculator.App
```

---

# 🧱 9. Design Pattern Utilizado

### **Strategy Pattern**

Cada operação (soma, subtração, multiplicação, divisão) implementa a interface:

```java
public interface Operation {
    double execute(double a, double b);
}
```

Isso permite adicionar novas operações sem alterar o código existente — seguindo o **Open/Closed Principle**.

---

# 🧪 10. Estrutura de Testes (Cobertura 100%)

* `OperationTests.java` testa individualmente cada operação
* `CalculatorTest.java` garante que o Calculator usa corretamente o Strategy

Com isso, Jacoco atinge **100% de cobertura** nas classes principais.

---

# 🎯 11. Comandos Importantes (Resumo)

| Ação                    | Comando                                |
| ----------------------- | -------------------------------------- |
| Rodar testes            | `mvn test`                             |
| Rodar testes + coverage | `mvn clean verify`                     |
| Ver relatório Jacoco    | abrir `target/site/jacoco/index.html`  |
| Rodar SonarQube         | `./sonar.sh start`                     |
| Rodar análise Sonar     | `mvn sonar:sonar -Dsonar.login=TOKEN`  |
| Rodar aplicação         | `java -cp target/*.jar calculator.App` |

---

```
para rodar: 
mvn clean verify sonar:sonar \
-Dsonar.login='squ_cc0aeb71877f0e315cc176752d141984c2b9cf38' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
