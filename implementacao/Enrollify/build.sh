#!/bin/bash

SRC_DIR="src"
BUILD_DIR="build"
CLASSES_DIR="$BUILD_DIR/classes"
JAR_DIR="$BUILD_DIR/jar"
DATA_DIR="$BUILD_DIR/data"
JAR_NAME="enrollify.jar"
MAIN_CLASS="Main"

clean() {
    rm -rf "$BUILD_DIR"
    find "$SRC_DIR" -name "*.class" -delete
    echo "✓ Limpeza concluída!"
}

compile() {
    mkdir -p "$CLASSES_DIR"
    javac -d "$CLASSES_DIR" -cp "$SRC_DIR" "$SRC_DIR"/*.java
    if [ $? -eq 0 ]; then
        echo "✓ Compilação concluída!"
    else
        echo "✗ Erro na compilação!"
        exit 1
    fi
}

jar() {
    mkdir -p "$JAR_DIR"
    jar -cfe "$JAR_DIR/$JAR_NAME" "$MAIN_CLASS" -C "$CLASSES_DIR" .
    echo "✓ JAR criado: $JAR_DIR/$JAR_NAME"
}

data() {
    mkdir -p "$DATA_DIR"
    javac -d "$CLASSES_DIR" -cp "$SRC_DIR" popular_dados.java
    java -cp "$CLASSES_DIR" popular_dados
    echo "✓ Dados populados!"
}

run() {
    if [ ! -f "$DATA_DIR/universidade.dat" ]; then
        echo "Dados não encontrados. Populando dados de teste..."
        data
    fi
    if [ -f "$JAR_DIR/$JAR_NAME" ]; then
        java -jar "$JAR_DIR/$JAR_NAME"
    else
        java -cp "$CLASSES_DIR" "$MAIN_CLASS"
    fi
}

case "$1" in
    "clean") clean ;;
    "compile") compile ;;
    "jar") jar ;;
    "run") run ;;
    "data") data ;;
    *) echo "Uso: $0 [clean|compile|jar|run|data]"; exit 1 ;;
esac
