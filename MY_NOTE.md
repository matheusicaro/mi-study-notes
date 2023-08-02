#  Razer Blade 17 2022 (RZ09-0423QED3-R3U1)

Intel Core i9-12900H <br>
Windows 11 Home <br>
17.3" 240 Hz QHD <br>
GeForce RTX 3070 Ti <br>
16 GB 4800 MHz RAM, 1 TB SSD <br>

https://www.razer.com/ca-en/gaming-laptops/Razer-Blade-17/RZ09-0423QED3-R3U1

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/b5f8e5ec-361e-4b06-b091-a7c6a52e86df)


### Description

![WhatsApp Image 2023-07-07 at 12 57 24 PM](https://github.com/matheusicaro/private-helpers/assets/29001162/4f8054aa-a3df-466a-903e-8ead7542930f)


# DRIVERS LINUX


#### UNISTALL NVIDIA DRIVER

```
sudo nvidia-uninstall
```

#### INSTALL NVIDIA DRIVER

1. show the drivers installed
  ```terminal
  lspci | grep VGA
  ```

2. deinstall the previous driver if necessary:

2. download the driver here: https://www.nvidia.com/Download/Find.aspx

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/82724969-0e6b-4b2f-bf36-da53a32219c0)


3. add permission to the script
   ```terminal
   sudo chmod +x NVIDIA<___tab-autocomplete___> 
   ```
4. Run the installer with: `sudo ./NVIDIA<___tab-autocomplete___>`
<br> 4.1. **How to disable** Nouveau kernel driver?*
<br> 4.1.1. run: `sudo nano /etc/modprobe.d/blacklist-nouveau.conf`
<br> 4.1.2. write in the file: 
     ```
      blacklist nouveau
      options nouveau modeset=0
     ```
<br> 4.1.3. run the command: `sudo update-initramfs -u`
<br> 4.1.4. reboot the system
