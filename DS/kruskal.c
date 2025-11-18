#include<stdio.h>
#include<stdlib.h>
#define MAX 20
// #define INF 99999
typedef struct{
    int u,v,w;
}edge;
int parent[MAX],rank[MAX],n,e;
edge heap[MAX];
edge t[MAX];
int heapSize=0;
void swap(edge *a,edge *b){
    edge temp=*a;
    *a=*b;
    *b=temp;
}
void heapify(int i){
    int left=2*i;
    int right=2*i+1;
    int min=i;
    if(left<=heapSize&&heap[left].w<heap[min].w)
        min=left;
    if(right<=heapSize&&heap[right].w<heap[min].w)
        min=right;
    if(min!=i){
        swap(&heap[min],&heap[i]);
        heapify(min);
    }    
}
void buildHeap(){
    for(int i=heapSize/2;i>=1;i--)
    heapify(i);
}
edge delMin(){
    edge minedge=heap[1];
    heap[1]=heap[heapSize];
    heapSize--;
    if(heapSize>=1)
    heapify(1);
    return minedge;
}
int find(int x){
    if(parent[x]!=x)
        parent[x]=find(parent[x]);
    return parent[x];
}
void unionSet(int x,int y){
    int rootx=find(x);
    int rooty=find(y);
    if(rootx==rooty)return;
    if(rank[rootx]<rank[rooty])parent[rootx]=rooty;
    else if(rank[rootx]>rank[rooty])parent[rooty]=rootx;
    else{
        parent[rootx]=rooty;
        rank[rooty]++;
    }
}
void kruskal(){
    int i=0,mincost=0; 
    buildHeap();
    while(i<n-1&&heapSize>0){
        edge e=delMin();
        int j=find(e.u);
        int k=find(e.v);
        if(j!=k){
            i++;
            t[i].u=e.u;
            t[i].v=e.v;
            t[i].w=e.w;
            mincost+=e.w;
            unionSet(j,k);
        }
    }
    if(i!=n-1){
        printf("No minimum cost spanning tree exists!\n");
        return;
    }
    printf("Edges in the Minimum cost spanning tree:\n");
    for(int m=1;m<=i;m++)
        printf("%d--%d: %d\n",t[m].u,t[m].v,t[m].w);
    printf("Total minimum cost:%d",mincost);
}
int main(){
    printf("Enter number of vertices:");
    scanf("%d",&n);
    printf("Enter number of edges:");
    scanf("%d",&e);
    heapSize=e;
    printf("Enter edges and their costs in the format(source destination cost):\n");
    for(int i=1;i<=e;i++){
        scanf("%d %d %d",&heap[i].u,&heap[i].v,&heap[i].w);
    }
    for(int i=1;i<e;i++){
        parent[i]=i;
    }
    kruskal();
}