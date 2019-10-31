@echo off
echo 正在清除SVN文件，请稍等......
for /r . %%a in (.) do @if exist "%%a\.svn" rd /s /q "%%a\.svn"
echo 清除完成!
echo. & pause