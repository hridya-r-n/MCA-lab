#include<stdio.h>
#include<stdlib.h>
typedef struct node{
    int data;
    struct node *next;
}node;
node *head=NULL;
int count=0;
node *createNode(int val){
    node *ptr=(node *)malloc(sizeof(node));
    ptr->data=val;
    ptr->next=NULL;
    return ptr;
}
void create(){
    int ch=1,val;
    while (ch==1)
    {
        printf("Enter value of the node:");
        scanf("%d",&val);
        node *ptr=createNode(val);
        if(head==NULL){
            head=ptr;
            count++;
        }
        node *temp=head;
        while(temp)
        temp=temp->next;
        temp->next=ptr;
        count++;
        printf("Do you want to add more nodes(1-yes,0-no)?:");
        scanf("%d",&ch);
    }
}
void insert(int val,int pos){
    node *ptr=createNode(val);
    if(pos==1){
        ptr->next=head;
        head=ptr;
        count++;
    }else{
        node *temp=head->next;
        for(int i=1;i<pos;i++)
        temp=temp->next;
        ptr->next=temp->next;
        temp->next=ptr;
        count++;
    }
}
void delNode(int val){
    if(!head){
        printf("List Empty!\n");
        return;
    }
    if(head->data==val){
        node *ptr=head;
        head=NULL;
        free(ptr);
        count--;
        return;
    }
    node *temp=head;
    while(temp->next!=NULL||temp->next->data!=val){
        temp=temp->next;
    }
    if(temp->next){
        node *ptr=temp->next;
        temp->next=ptr->next;
        free(ptr);
        count--;
        return;
    }else{
        printf("%d not in list!\n",val);
        return;
    }
}
void traverse(){
    if(!head){
        printf("Tree Empty!\n");
        return;
    }
    node *temp=head;
    printf("Elements are:\t");
    while(temp){
        printf("%d ",temp->data);
        temp=temp->next;
    }
}
int main(){
    int val,ch,pos;
    do{
        printf("----------Singly Linked List------------\n1.Create\n2.Insert\n3.Delete\n4.Count\n5.Traverse\n6.Exit\nEnter your choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1:create();
            break;
            case 2:printf("Enter value of the node:");
            scanf("%d",&val);
            printf("Engter position to insert:");
            scanf("%d",&pos);
            if(pos<0||pos>count+1){
                printf("Invalid position\n");
                break;
            }
            insert(val,pos);
            break;
            case 3:if(!head){
                printf("List Empty!\n");
                break;
            }
            printf("Enter value to delete:");
            scanf("%d",&val);
            delNode(val);
            break;
            case 4:printf("Number of nodes:%d\n",count);
            break;
            case 5:traverse();
            break;
            case 6:return 0;
            default:printf("Invalid Choice!\n");
        }
    }while(ch!=6);
}