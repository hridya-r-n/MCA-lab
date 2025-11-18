#include<stdio.h>
#define INF 99999
#define MAX 50
int minNear(int cost[MAX][MAX],int near[MAX],int v){
    int min=INF,index=-1;
    for(int i=0;i<v;i++){
        if(near[i]!=-1&&cost[i][near[i]]<min){
            min=cost[i][near[i]];
            index=i;
        }
    }
    return index;
}
void printMST(int cost[MAX][MAX],int v,int t[MAX][2],int mincost){
    printf("\nMinimum Cost Spanning Tree:\n");
    printf("Edge\tCost\n");
    for(int i=0;i<v-1;i++){
        printf("%d - %d\t%d\n",t[i][0],t[i][1],cost[t[i][0]][t[i][1]]);
    }
    printf("\nTotal minimum cost:%d\n",mincost);
}
void prims(int cost[MAX][MAX],int v){
    int near[MAX],t[MAX][2],min=INF,mincost=0;
    int k=0,l=0;
    for(int i=0;i<v;i++){
        for(int j=0;j<v;j++){
            if(cost[i][j]<min){
                min=cost[i][j];
                k=i;l=j;
            }
        }
    }
    t[0][0]=k;
    t[0][1]=l;
    mincost=cost[k][l];
    
    for(int i=0;i<v;i++){
        if(cost[i][l]<cost[i][k])
         near[i]=l;
        else 
         near[i]=k;
    }
    near[k]=near[l]=-1;

    for(int i=1;i<v-1;i++){
        int j=minNear(cost,near,v);
        t[i][0]=j;
        t[i][1]=near[j];
        mincost+=cost[j][near[j]];
        for(int m=0;m<v;m++){
            if(near[m]!=-1&&cost[m][near[m]]>cost[m][j]){
                near[m]=j;
            }
        }
        near[j]=-1;
    }
    printMST(cost,v,t,mincost);
}
int main(){
    int v,cost[MAX][MAX];
    printf("Enter number of vertices:");
    scanf("%d",&v);
    printf("Enter the cost adjacency matrix(Enter 0 if there is no edge):");
    for(int i=0;i<v;i++){
        for(int j=0;j<v;j++){
            scanf("%d",&cost[i][j]);
            if(cost[i][j]==0){
                cost[i][j]=INF;
            }
        }
    }
    prims(cost,v);
}