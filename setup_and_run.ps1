$ErrorActionPreference = "Stop"

# Maven Configuration
$MavenUrl = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
$MavenZip = "apache-maven-3.9.6-bin.zip"
$MavenDir = "apache-maven-3.9.6"

# Java Configuration
$JavaUrl = "https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip"
$JavaZip = "openjdk-17.0.2_windows-x64_bin.zip"
$JavaDir = "jdk-17.0.2"

# --- Maven Setup ---
Write-Host "Checking for Maven..."
if (!(Test-Path $MavenDir)) {
    Write-Host "Maven not found. Downloading from $MavenUrl..."
    try {
        Invoke-WebRequest -Uri $MavenUrl -OutFile $MavenZip
    }
    catch {
        Write-Error "Failed to download Maven. Check URL/Connection."
        exit 1
    }
    
    Write-Host "Extracting Maven..."
    Expand-Archive -Path $MavenZip -DestinationPath . -Force
    Remove-Item $MavenZip
}

# --- Java Setup ---
Write-Host "Checking for Java 17..."
if (!(Test-Path $JavaDir)) {
    Write-Host "Java 17 not found. Downloading from $JavaUrl..."
    try {
        Invoke-WebRequest -Uri $JavaUrl -OutFile $JavaZip
    }
    catch {
        Write-Error "Failed to download Java. Check URL/Connection."
        exit 1
    }
    
    Write-Host "Extracting Java..."
    Expand-Archive -Path $JavaZip -DestinationPath . -Force
    Remove-Item $JavaZip
}

# --- Environment Setup ---
$env:JAVA_HOME = "$PWD\$JavaDir"
$env:Path = "$PWD\$MavenDir\bin;$env:JAVA_HOME\bin;$env:Path"

# --- Verify ---
Write-Host "Verifying Environment..."
mvn -version
java -version

# --- Build & Run ---
Write-Host "Building Project..."
mvn clean install -DskipTests

Write-Host "Running Application..."
mvn spring-boot:run
