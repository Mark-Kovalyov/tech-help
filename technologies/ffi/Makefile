build: clean
	gcc -Wall -g -c prime.c -o prime.o
	gcc -shared prime.o -L/usr/local/lib -o prime.so

c: build
	gcc prime-main.c -Lprime.so -o prime-main.exe
	./prime-main.exe

clean:
	rm -f prime.o prime.so prime-main.o prime.hi
	rm -f *.class

hs: build
	ghc prime.hs prime-main-hs.hs
	./prime-main-hs

ruby: build
	ruby prime.rb

java-panama: build
	# FFI with Project Panama
	# 1) Generate Java API for gcd
	#jextract --source -t mayton.native.math -I /usr/include gcd.h
	#javac GcdPanama.java

java-jni: build
	javac -h . PrimeJni.java PrimeMain.java
	java -Djava.library.path="." -cp . PrimeMain

python: build
	python prime-main.py

go: build
	#go build -o prime-go.so -buildmode=c-shared prime-main.go
	go build prime-main.go

build-all: ruby java python go