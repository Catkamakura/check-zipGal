# check-zipGal
Check MD5 and zip galgame from 忧郁的弟弟

## what should I do?
**You should change and set the rootDir in the Main.java which is the root folder of the galgame, all work after will working base on this directory.**

## How does this project work?
Runing the main() method, at First InfoReader will read all folder in the rootDir into dirList array, for each folder in the array, the project will first reader and find expected MD5 for each part of the RAR file, saving it into array named md5List. Then it will read all the RAR file and calculate the actual MD5 value of each RAR file, saving it and its part number(e.g. part1 part2) into array named rarList. Then the project will check if there exit enough RAR file as the README.md said and check each RAR's md5 value. If check failed, you will receive a massage on the screen, if check succeed, the project will try to zip the gal's folder into a .zip file, saving the zip file under the root folder

## Is there any example?
For example, if I hava a rootDir named "E:/galgame", in which there is a folder contains one galgame called "A048@Little Busters！EX"

see the index tree below

E: -
   |-galgame-
            |-A048@Little Busters！EX-
                                     |-A048@忧郁的弟弟.part1.rar
                                     |-A048@忧郁的弟弟.part2.rar
                                     |-A048@忧郁的弟弟.part3.rar
                                     |-A048@忧郁的弟弟.part4.rar
                                     |-A048@忧郁的弟弟.part5.rar
                                     |-README.md
