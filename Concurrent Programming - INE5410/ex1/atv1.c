#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>

double media(int grades[], int num_grades){
	double sum = 0;

  for(int a=0; a < num_grades; a++){
    sum+=grades[a];
  }

  double average;
  average = sum/num_grades;

  return average;

}

double desvio(int grades[], int num_grades){
  double result = 0, sum = 0;
  double avrg = media(grades, num_grades);

  for(int a = 0; a < num_grades; a++){
    sum += pow((grades[a] - avrg), 2);
  }
  result = sqrt(sum/num_grades);

  return result;
}



int main(int argc,	char	*argv[]){
	if(argc == 1){
		do{
			int aux1 = atoi(scanf());
		}while(aux1 != 0);

	}else{
	  srand(time(NULL));

	  int num_grades = atoi(argv[1]); //tamanho do vetor
	  int* grades = malloc(num_grades*sizeof(int)); //aloca espaco suficiente para um array de tamanho 'num_grades' de int

	  for(int a = 0; a < num_grades; a++){
	    grades[a] = rand()%10 ;
	  }

	  double media1 = media(grades, num_grades);
	  double desvio_padrao1 = desvio(grades, num_grades);

	  printf("A media das notas é %f\n", media1 );
	  printf("O desvio padrão das notas é %f\n", desvio_padrao1);
	}

  return 0;
}
