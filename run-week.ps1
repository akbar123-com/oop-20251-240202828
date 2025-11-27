param(
    [string]$Week = "week2-class-object"
)

# Usage: .\run-week.ps1 -Week week3-inheritance
Write-Host "Running week: $Week"

$repoRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$srcRoot = Join-Path $repoRoot "praktikum\$Week\src\main\java"
if (-not (Test-Path $srcRoot)) {
    # fallback: some weeks put sources directly under src
    $srcRoot = Join-Path $repoRoot "praktikum\$Week\src"
}

if (-not (Test-Path $srcRoot)) {
    Write-Error "Source folder not found for week: $Week (expected under praktikum\$Week\src or src\main\java)"
    exit 1
}

$out = Join-Path $repoRoot "praktikum\$Week\out"
if (Test-Path $out) { Remove-Item -Recurse -Force $out }
New-Item -ItemType Directory -Path $out | Out-Null

# collect java files
$javaFiles = Get-ChildItem -Path $srcRoot -Recurse -Include *.java -File | ForEach-Object { $_.FullName }
if (-not $javaFiles) {
    Write-Error "No Java files found under $srcRoot"
    exit 1
}

Write-Host "Compiling ${javaFiles.Count} java files..."
& javac -d $out $javaFiles
if ($LASTEXITCODE -ne 0) { Write-Error "Compilation failed"; exit $LASTEXITCODE }

# find a Main*.java file to run
$mainFile = Get-ChildItem -Path $srcRoot -Recurse -Include *Main*.java -File | Select-Object -First 1
if (-not $mainFile) {
    Write-Warning "No Main* class file found; listing compiled classes:";
    Get-ChildItem -Path $out -Recurse -Filter *.class | ForEach-Object { Write-Host $_.FullName }
    exit 0
}

# derive fully qualified class name from file path
$rel = Resolve-Path -Relative -Path $mainFile.FullName -RelativeBase $srcRoot 2>$null
if (-not $rel) {
    # fallback compute by string replacement
    $rel = $mainFile.FullName.Substring($srcRoot.Length+1)
}
$fqcn = ($rel -replace '\\','.') -replace '\.java$',''

Write-Host "Running $fqcn"
& java -cp $out $fqcn
