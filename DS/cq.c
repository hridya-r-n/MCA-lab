#include<stdio.h>
#define MAX 5
int cq[MAX],count=0,front=-1,rear=-1;
void enqueue(int val){
    if(front==0&&rear==MAX-1||rear==front-1){
        printf("Queue Overflow!\n");
        return;
    }else if(rear==-1){
        front=rear=0;
    }else if(rear==MAX-1){
        rear=0;
    }else
    rear+=1;
    cq[rear]=val;
    printf("%d inserted\n",val);
    count++;
}
void dequeue(){
    if(front==-1){
        printf("Queue Underflow!\n");
        return;
    }
    int val=cq[front];
    printf("%d deleted\n",val);
    if(front==rear){
        front=rear=-1;
    }else if(front==MAX-1)
    front=0;
    else
    front+=1;
    count--;
}
void traverse(){
    if(front==-1){
        printf("Queue Empty!\n");
        return;
    }
    printf("Queue Eelement: ");
    if(front<rear){
        for(int i=front;i<=rear;i++)
        printf("%d ",cq[i]);
    }else{
        for(int i=0;i<=rear;i++)
        printf("%d ",cq[i]);
        for(int i=front;i<MAX-1;i++)
        printf("%d ",cq[i]);
    }
}
int main(){
    int ch,val;
    do{
        printf("\nCircular Queue\n1.Enqueue\n2.Dequeue\n3.Traverse\n4.Count\n5.Exit\nEnter your choice:");
        scanf("%d",&ch);
        switch (ch)
        {
        case 1:
            printf("Enter value:");
            scanf("%d",&val);
            enqueue(val);
            break;
        case 2:
            dequeue();
            break;
        case 3:
            traverse();
            break;
        case 4:
            printf("Number of elements:%d",count);
            break;
        case 5:return 0;
        default:printf("Invalid Choice!\n");
        }
    }while(ch!=5);
}