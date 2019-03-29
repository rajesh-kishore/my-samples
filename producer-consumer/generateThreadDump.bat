REM The following batch script generates 6 thread dumps at a preset interval of 20 secs
REM script can be executed as
REM threaddump-windows-jstack.bat 2120
REM where 2120 is the java process id
mkdir dumps
for /L %%i in (1,1,6) do (
  echo Taking Thread Dump %i
  
  "%JAVA_HOME%/bin/jstack" -l %1 > dumps\thread-dump-%%i.txt

   timeout 10
)
