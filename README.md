# Soccer League Project with JPA

#### This repository is for the Soccer League project written in Java that includes JPA.

### When installing this project locally:
1. Make a **/lib** folder in your main project directory.
2. Download an **EclipseLink Installer** that includes **Jakarta**.
    - https://www.eclipse.org/eclipselink/downloads/
    - Unzip the downloaded folder.
    - Move eclipselink/jlib/**eclipselink.jar** into the **/lib** folder in your project.
    - Move eclipselink/jlib/jpa/**jakarta.persistence-api.jar** into the **/lib** folder in your project.
3. Download the MySQL JDBC download ZIP package.
    - https://dev.mysql.com/downloads/connector/j/
    - Select **Platform Independent** for the Operating System type.
    - Download the ZIP file.
    - Unzip the downloaded folder.
    - Move mysql-connector-j-8.0.31/**mysql-connector-j-8.0.31.jar** into the **/lib** folder in your project.
4. Add all 3 **.jar** files in the **/lib** folder to the project's **Class Path**.
    - Right click on the **.jar** file.
    - Select **Build Path** --> **Add to Build Path**.
5. Make sure you have a local database named **league**.
    - We recommend you download and install XAMPP to help manage your databases.
    - https://www.apachefriends.org/download.html
