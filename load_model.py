import torch
import sys
from PIL import Image
import torchvision.transforms.functional as TF
import torchvision
from matplotlib import pyplot as plt

image = Image.open(sys.argv[2])
x = TF.to_tensor(image)
x.unsqueeze_(0)
print(x.shape)

model = torch.load(sys.argv[1])
model.eval()

output = model(x)
torchvision.utils.save_image(output, 'output.png')
