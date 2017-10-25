;Programmet utfører noe mystisk...

; Konstanter
cr equ 13 ; Vognretur
lf equ 10 ; Linjeskift
SYS_EXIT equ 1
SYS_READ equ 3
SYS_WRITE equ 4
STDIN equ 0
STDOUT equ 1
STDERR equ 2

; Datasegment
section .bss
  siffer resb 4

; Datasegment
section .data
  crlf db cr,lf
  crlflen equ $ - crlf

; Kodesegment med program
section .text
global _start
_start:
  mov eax,0 ; Sett resultat til null

  mov ecx, 20
  start_loop:
    cmp ecx,10 ; test ecx, 10 >> LT, GT, EQ
    jg upp ; If GT then jump to upp
    add eax,1 ; eax ++
    jmp end ;Jump to end

    upp:
    sub eax,1 ; eax --
    end:
  loop start_loop

  mov ecx, eax ; Til utskrift

Slutt:
  call skrivsiffer ; Skriv ut verdi i ecx som ensifret tall
  mov eax,SYS_EXIT
  mov ebx,0
  int 80h

; ---------------------------------------------------------
skrivsiffer:
  ; Skriver ut sifferet lagret i ecx. Ingen sjekk på verdiområde.
  push eax
  push ebx
  push ecx
  push edx

  mov edx, 0
  mov eax, ecx
  mov ebx, 10
  div ebx

  add edx,'0'
  add eax,'0' ; converter tall til ascii.

  push edx
  push eax

prsiffer:
  pop ecx
  mov [siffer],ecx
  mov ecx,siffer
  mov edx,1
  mov ebx,STDOUT
  mov eax,SYS_WRITE
  int 80h

  pop ecx
  mov [siffer],ecx
  mov ecx,siffer
  mov edx,1 ; Because loops are for loosers
  mov ebx,STDOUT
  mov eax,SYS_WRITE
  int 80h

  pop edx
  pop ecx
  pop ebx
  pop eax
  call nylinje ; Finere utskrift
  ret

; ---------------------------------------------------------
; Flytt cursor helt til venstre på neste linje
nylinje:
  push eax
  push ebx
  push ecx
  push edx
  mov edx,crlflen
  mov ecx,crlf
  mov ebx,STDOUT
  mov eax,SYS_WRITE
  int 80h
  pop edx
  pop ecx
  pop ebx
  pop eax
  ret
