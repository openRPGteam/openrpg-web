from PIL import Image, ImageFilter

EMOJI_DICT = {
    (124,179,66) : "\U0001f922", # 🤢
    (133,93,83) : "\U0001F4A9", # 💩
    (112,83,74) : "\U0001F466", # 👦
    (251,215,113) : "\U0001F474", # 👴
    (250,220,188) : "\U0001F474", # 👴 duplicate? 
    (146,104,202) : "\U0001F930", #	🤰
    (226,234,237) : "\U0001F6C0", # 🛀
    (250,192,54) : "\U0001F449", # 👉
    (250,220,188) : "\U0001F448", # 👈
    (112,83,74) : "\U0000270B", #  ✋
    (55,180,226) : "\U0001F499", # 💙
    (177,201,55) : "\U0001F49A", #  💚
    (119,66,204) : "\U0001F49C", # 💜
    (23,23,23) : "\U0001F5A4", # 🖤
    (231,90,112) : "\U0001F493", # 💓
    (239,242,244) : "\U00002601", # ☁ 
    (219,68,55) : "\U00002665", # ♥
    (255,152,0) : "\U0001F536", # 🔶
    (190,190,190) : "\U00002B1C" # ⬜
}

def distance(lcl, rcl):
    """returns distance between two colours"""
    return abs(lcl[0] - rcl[0]) + abs(lcl[1] - rcl[1]) + abs(lcl[2] - rcl[2])

def appr_color(rgba_tuple):
    """returns an emoji based on R, G, B values"""
    distances = [distance(rgba_tuple, d) for d in EMOJI_DICT.keys()]
    return EMOJI_DICT[list(EMOJI_DICT.keys())[distances.index(min(distances))]]

def convert_image(imgfile, size):
    """converts imgfile to emoji string
    size must be a two-element tuple"""
    imghandle = Image.open(imgfile)
    x, y = imghandle.size
    x /= size[0]; y /= size[1]
    zones = [(x0 * x, y0 * y, x * (x0 + 1), y * (y0 + 1)) for x0 in range(size[0]) for y0 in range(size[1])]
    colours = [imghandle.crop(box).filter(ImageFilter.GaussianBlur(50)).getpixel((0,0)) for box in zones]
    imghandle.close()
    emoji_map = ""
    for i in range(len(colours)):
        emoji_map += '\r\n' if i % size[0] == 0 and i != 0 else appr_color(colours[i])
    return emoji_map

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 4:
        print("Usage: img2emoji.py [image] [columns] [rows]")
    else:
        print(convert_image(sys.argv[1], (int(sys.argv[2]), int(sys.argv[3]))) )

