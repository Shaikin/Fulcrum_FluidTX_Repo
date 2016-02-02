ECHO ON
for /f %%i in ("%0") do set curpath=%~dp0
cd /d %curpath%
ant