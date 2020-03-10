# check-zipGal
Check MD5 and zip galgame from 忧郁的弟弟

## what should I do?
**You should change and set the rootDir in the Main.java which is the root folder of all galgames downloaded from 忧郁的弟弟 website, all other works are base on this directory.**

## How does this project work?
Runing the main() method, at First InfoReader will read all galgamefolder in the rootDir into dirList array, for each galgamefolder in the array, the project will first read README.md and find expected MD5 for each RAR file.Then it will read all the RAR file and calculate  each RAR file's actual MD5 value. Then the project will check if there exit enough RAR file as the README.md said and check each RAR's md5 value. If check failed, you will receive a massage on the screen, if check succeed, the project will try to zip the gal's folder into a .zip file, saving the zip file under the root folder

## Is there any example?
For example, the rootDir is "E:/galgame", in the rootDir there is a folder contains one galgame called "A048@Little Busters！EX"

the index tree is something like below

<blockquote>
<pre>
E: -
   |-galgame-
            |-A048@Little Busters！EX-
                                     |-A048@忧郁的弟弟.part1.rar
                                     |-A048@忧郁的弟弟.part2.rar
                                     |-A048@忧郁的弟弟.part3.rar
                                     |-A048@忧郁的弟弟.part4.rar
                                     |-A048@忧郁的弟弟.part5.rar
                                     |-README.md
</pre>
</blockquote>
