nasm –f elf –F dwarf –g hello.asm
ld –m elf_i386 –o hello hello.o
gdb –tui hello
