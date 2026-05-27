def isPalindrome(s):
    if s[0].lower()==s[-1].lower():
        return True
    else:
        return False
s="malayalam"
print(f"{s} is palindrome" if isPalindrome(s) else f"{s} is not palindrome")