# Coding Standards

## Naming
### packages - lowerCamelCase (myPackage)
### variables - lowerCamelCase (myField)
### methods - lowerCamelCase (myMethod)
### classses - UpperCamelCase (ex. MyClass)
### interfaces - IUpperCamelCaseable (ex. IMyInterfaceable)

## Formatting
```
void thisIsCorrectBracketUsage(){
  // code
}

void thisIsIncorrectBracketUsage ()
{
  // code
}

/*
 * This is comment 
 * outside function
 */

if(amIGreat == true){   // <-- note those spaces
  thatsNormal();        // this is comment 
  totally();            // inside function
}

/*
 * For constant values
 * use public static final fields
 */

```
