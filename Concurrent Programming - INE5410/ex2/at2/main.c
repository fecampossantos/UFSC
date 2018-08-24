#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
                          (principal)
//                               |
//              +----------------+--------------+
//              |                               |
//           filho_1                         filho_2
//              |                               |
//    +---------+-----------+          +--------+--------+
//    |         |           |          |        |        |
// neto_1_1  neto_1_2  neto_1_3     neto_2_1 neto_2_2 neto_2_3

// ~~~ printfs  ~~~
//      principal (ao finalizar): "Processo principal %d finalizado\n"
// filhos e netos (ao finalizar): "Processo %d finalizado\n"
//    filhos e netos (ao inciar): "Processo %d, filho de %d\n"

// Obs:
// - netos devem esperar 5 segundos antes de imprmir a mensagem de finalizado (e terminar)
// - pais devem esperar pelos seu descendentes diretos antes de terminar

int main(int argc, char** argv) {
  //processo pai cria o filho_1
  int pid1 = fork();

  if (pid1>0){ //processo pai
    int status1;
    wait(&status1);

    //processo pai cria o filho_2
    int pid2 = fork();

    if (pid2 > 0){ //processo pai
      int status2;
      wait(&status2);



    } else if (pid2 == 0){ //processo filho_2
      printf("Processo %d, filho de %d\n" ); // COLOCAR ARGUMENTOS!!!

      int pid6 = fork()
      if (pid6 > 0) { //processo filho_2

        int pid7 = fork();
        if (pid7 > 0){//processo filho_2

          int pid8 = fork();
          if (pid8 > 0){ //processo filho_2

          } else if (pid8 == 0){ //processo neto_2_3
          printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!

          }
        } else if (pid7 == 0){//processo neto_2_2
           printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!

        }
      } else if (pid6 == 0){//processo neto_2_1
        printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!

      }
    }

  } else if (pid1 == 0){ //processo filho_1
    printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!

    int pid3 = fork()
    if (pid3 > 0){ //processo filho_1
      printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!

      int pid4 = fork();
      if (pid4 > 0) { //processo filho_1

        int pid5 = fork();

        if (pid5 > 0){ //processo filho_1

        } else if (pid5 == 0){ //processo neto_1_3
          printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!
        }

      } else if (pid4 == 0){ //processo neto_1_2
        printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!
      }

    } else if (pid3 == 0){ //processo neto_1_1
      printf("Processo %d, filho de %d\n" getpid(), getppid()); // COLOCAR ARGUMENTOS!!!
    }

  }

  printf("Processo principal %d finalizado\n", getpid());
  return 0;
}
