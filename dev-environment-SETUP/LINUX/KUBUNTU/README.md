# KUBUNTU 23.04

## NVIDIA DRIVER VIDEO

#### UNINSTALL NVIDIA DRIVER

```
sudo nvidia-uninstall
```

<br>
<br>

#### INSTALL NVIDIA DRIVER

1. show the drivers installed

```terminal
lspci | grep VGA
```

2. UNINSTALL the previous driver if necessary:

3. download the driver here: https://www.nvidia.com/Download/Find.aspx

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/82724969-0e6b-4b2f-bf36-da53a32219c0)

3. add permission to the script
   ```terminal
   sudo chmod +x NVIDIA<___tab-autocomplete___>
   ```
4. Run the installer with: `sudo ./NVIDIA<___tab-autocomplete___>`
   <br> 4.1. **How to disable** Nouveau kernel driver?\*
   <br> 4.1.1. run: `sudo nano /etc/modprobe.d/blacklist-nouveau.conf`
   <br> 4.1.2. write in the file:
   `      blacklist nouveau
      options nouveau modeset=0
    `
   <br> 4.1.3. run the command: `sudo update-initramfs -u`
   <br> 4.1.4. reboot the system

---

<br>
<br>
<br>
<br>
<br>
<br>

---

### 1. install a new task monitor

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/e8dbfbf1-5fd9-464a-b9e8-f0dd7ad4f83b)

### 2. TASK BAR

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/203343cd-d22a-450b-949a-84fad94a0663)

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/26b4eaf9-bc97-4f60-9cf0-c35e3d88f960)
![image](https://github.com/matheusicaro/private-helpers/assets/29001162/e1e02e3c-d533-4159-9d7a-e5f5162703f9)
![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c7c7e76d-8d7c-4b45-9f54-af379d075954)

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/0d83f051-6f34-4255-b9fb-57bdbb58605a)
![image](https://github.com/matheusicaro/private-helpers/assets/29001162/d947db92-813b-4b1c-9bf5-fc71341c7f15)
![image](https://github.com/matheusicaro/private-helpers/assets/29001162/671f1521-91a2-498b-85e6-00c16317a1e6)

### [ 1 ]

![Screenshot_20230628_194036](https://github.com/matheusicaro/private-helpers/assets/29001162/f36ed6a8-89a3-420e-8433-564437ca3d9f)

### [ 2 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/41fc9273-91b2-4c85-a6b5-d51587412b75)

### [ 3 ]

![Screenshot_20230628_194507](https://github.com/matheusicaro/private-helpers/assets/29001162/7583b13f-63de-4e38-a620-c8f43ce4c5a2)

### [ 4 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/e37d30ab-32d9-45a8-bdf0-776abd5a435e)

### [ 5 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c84600f5-db57-47f8-b07f-2f3b89cf43ac)

#### shortcuts backup here

- [shortcuts.shortcuts.kksrc](https://github.com/matheusicaro/private-helpers/tree/master/LINUX/KUBUNTU/shortcuts.shortcuts.kksrc)
- [shortcuts.customShortcuts](https://github.com/matheusicaro/private-helpers/tree/master/LINUX/KUBUNTU/shortcuts.customShortcuts)

### [ 6 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/7b78af80-8d4e-4f41-8341-9b90634904af)

### [ 7 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c2bc843d-02a6-4344-a3fc-ce27696a94b0)

### [ 8 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/e9d6cf6a-7815-4b89-89e3-e8ccabb35cd9)

### [ 9 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/7493c53e-679c-4af0-a530-3d738c1ffac2)

### [ 10 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/14930827-ec45-4dd8-9a12-c1a9f9b4ea71)

### [ 11 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c65932c1-7363-4ebf-b882-5bff7f6a69e7)

### [ 12 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/5768aadd-0459-45ab-809d-a8b76bd9f9bc)

### [ 13 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/906f9a8f-1169-4902-be27-66b234777e48)

### [ 14 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/e577f1f7-9782-460e-bdd8-3c0a2b9fc7f0)

### [ 15 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/4c4b206c-95fa-4457-aa9c-9035c5677536)

### [ 16 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/03bc1dae-49c0-48cc-b6f8-184a9754123b)

### [ 17 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/f58aca43-39bd-416d-a572-d73024c743a6)

### [ 18 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/bfba8c62-6114-4c57-b831-394fdca4dd07)

### [ 19 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/76ce52f0-48b9-4912-b23a-bc5343e0e966)

### [ 20 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c752b495-4684-477f-bbb1-6265ca59cf04)

### [ 21 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/c0b44bdf-9137-4fcb-9f71-e8e7d7a124bf)

### [ 22 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/6a1eb3b4-d086-4fb1-9c51-8c36b2ec1749)

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

### [ 23 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/ef4e314f-aed6-443b-93a2-5c5115cf01f0)

### [ 24 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/0b46c6c7-0e9b-436a-99e8-07ed06951b84)

### [ 25 ]

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/97b670c9-9de6-4f11-ad72-e3aaab6b092f)

### [ 26 ]

![Screenshot_20230628_195450](https://github.com/matheusicaro/private-helpers/assets/29001162/e42b43bc-8491-4549-8b55-f528f846d9e7)

### [ 27 ]

![Screenshot_20230628_195550](https://github.com/matheusicaro/private-helpers/assets/29001162/fbe05ea8-ebc1-452c-bae3-2fe15e6abdef)

### [ 28 ]

![Screenshot_20230628_200848](https://github.com/matheusicaro/private-helpers/assets/29001162/5560536c-29f7-4fa7-a17a-0ab6635ef170)

### [ 29 ]

![Screenshot_20230628_200915](https://github.com/matheusicaro/private-helpers/assets/29001162/d6e475bd-ae8f-4ca4-a727-b8537f3060cb)

### [ 30 ]

![Screenshot_20230628_200929](https://github.com/matheusicaro/private-helpers/assets/29001162/b5792d0c-1638-44ab-8ddb-97effa967cf0)
