require recipes-bsp/u-boot/u-boot.inc
require recipes-bsp/u-boot/u-boot-toradex_${PV}.inc

LOCALVERSION = "-2.8.3"

PROVIDES += "u-boot"

inherit fsl-u-boot-localversion dtc-145

# U-Boot is flashed 1k into a NAND block, create a binary which prepends
# U-boot with 1k of zeros to ease flashing
nand_padding () {
    dd bs=1024 count=1 if=/dev/zero | cat - u-boot.imx > u-boot-nand.imx
}

do_compile_append_colibri-imx6ull () {
    nand_padding
}

do_compile_append_colibri-imx7 () {
    nand_padding
}

do_compile_append_colibri-vf () {
    nand_padding
}
