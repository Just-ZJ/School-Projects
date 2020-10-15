clc
clear
% Version 2.2
% Date: 1 November 2019
% Bug Fixed: Players can no longer put their pieces on the box that has a
% piece on it
% A function to check if the box is occupied is also added


%gets nicknames of players through user input
player1 = input('Player 1, please enter your nickname: ', 's');
player2 = input('Player 2, please enter your nickname: ', 's');
%assigns color to each player
fprintf('%s, you will be black.  \n', player1)
fprintf('%s, you will be red \n', player2)

% Initialize scene
my_scene = simpleGameEngine('ConnectFour.png',86,101);

% Set up variables to name the various sprites
empty_sprite = 1;
red_sprite = 2;
black_sprite = 3;

% Display empty board   
board_display = empty_sprite * ones(6,7);
drawScene(my_scene,board_display)





for(i = 1: 1: 100)
    %get mouse input
    [row,col] = getMouseInput(my_scene);
    empty = checkForSprite(row,col,board_display);
    while(empty == false)
        [row,col] = getMouseInput(my_scene);
        empty = checkForSprite(row,col,board_display);
    end

    if(mod(i, 2) ~= 1)
        %display updated board with player1 choice
        board_display(row,col) = black_sprite;
    elseif(mod(i, 2) == 1)
        %display updated board with player2 choice
        board_display(row,col) = red_sprite;
    end
    
    %displays the updated board
    drawScene(my_scene,board_display)
end



function [empty] = checkForSprite(x,y,board_display)
empty = true;
%checks if block in row 6 that the user picked is empty
if(x == 6)
    if(board_display(x, y) == 1)
        empty = true;
    else
        empty = false;
    end
%checks if the block below the user choice is empty
elseif(x < 6)
    if(board_display(x+1, y) == 1)
        empty = false;
    else
        if(board_display(x, y) == 1)
            empty = true;
        else
            empty = false;
        end
    end
end
end



